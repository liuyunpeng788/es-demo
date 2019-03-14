package com.stargazer.personal.esdemo.service;

import com.stargazer.personal.esdemo.dto.UserDto;
import com.stargazer.personal.esdemo.repository.UserEsRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

import static com.stargazer.personal.esdemo.constant.Constant.*;
import static com.stargazer.personal.esdemo.util.CommonUtils.parseEsDate;

@Service
public class EsBaseService {
    @Autowired
    UserEsRepository userEsRepository;

    public List<UserDto> findByName(String name) {
        return userEsRepository.queryUserDtoByName(name);
    }

    @Transactional
    public void putAll(List<UserDto> list) {
        userEsRepository.saveAll(list);
     }

    @Transactional
    public void deleteAll() {
        userEsRepository.deleteAll();
    }

    public void search(String strQuery) {

        //MatchQueryBuilder输入的词条会被es解析并进行分词，且所有大些会被自动转换成全小写
//        QueryBuilder builder = new MatchQueryBuilder("description", strQuery);

        //短语查询，输入的词条不会被分词处理，而是直接作为一个短语进行在倒排索引中查询，如果没有，就返回一个空
        QueryBuilder builder = new MatchPhraseQueryBuilder("description", strQuery);
        for (UserDto dto : userEsRepository.search(builder)) {
            System.out.println(dto);
        }

    }

    /**
     * 查询多个词同时出现的文章
     *
     * @param strQuery 查询词
     */
    public void searchMultiTerm(String strQuery) {
        Iterator<UserDto> iter = builderQuery(strQuery);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    /**
     * 根据查询词，查询相应的结果
     *
     * @param strQuery 查询词，包含分隔符
     * @return 查询的结果列表
     * @exception : 当查询结果为空时，可能抛出null异常
     */
    private Iterator<UserDto> builderQuery(String strQuery) {
        String[] terms = splitTerms(strQuery); //根据多个分隔符分隔后，多个关键词一块查询
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        for (String term : terms) {
            builder.must(new MatchPhraseQueryBuilder("description", term));
        }
        return userEsRepository.search(builder).iterator();

    }

    /**
     * 将待查询的字符串根据指定分隔符进行分隔
     *
     * @param strQuery 待查询的字符串
     * @return 字符串数组
     */
    private String[] splitTerms(String strQuery) {
        if (StringUtils.isEmpty(strQuery)) {
            return new String[0];
        }
        StringBuilder sb = new StringBuilder("[").append(STR_AND)
                .append(",").append(STR_COMMA)
                .append(",").append(STR_COMMA_CH)
                .append(",").append(STR_BLANK).append("]");
        return strQuery.split(sb.toString()); //根据多个分隔符分隔后，多个关键词一块查询
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 高亮查询命中词
     *
     * @param strQuery 查询的词
     */
    public void highLightTermsQuery(String strQuery) {
         String preTag = "<em><font color='#dd4b39' size='1.1em'>";//google的色值
        String postTag = "</font></em>";
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        String[] terms = splitTerms(strQuery);
        BoolQueryBuilder queryBuilders = new BoolQueryBuilder();
        for (String term : terms) {
            queryBuilders.must(new MatchPhraseQueryBuilder("description", term));
        }
        //按相关性、年龄、生日 倒叙排序
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0,10, new Sort(Sort.Direction.DESC,"_score","age","birthday"))); // sort 支持多个字段排序
        nativeSearchQueryBuilder.withQuery(queryBuilders);
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("description").preTags(preTag).postTags(postTag));
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        /**
         * 实现方式1：较复杂
         */

        elasticsearchTemplate.queryForPage(searchQuery, UserDto.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    UserDto user = new UserDto();
                    user.setId(searchHit.getId());

                    user.setBirthday(parseEsDate((String)searchHit.getSourceAsMap().get("birthday")));
                    user.setName((String)searchHit.getSourceAsMap().get("name"));
                    user.setAge((Integer) searchHit.getSourceAsMap().getOrDefault("age",0));
                    user.setDescription(searchHit.getHighlightFields().get("description").fragments()[0].toString());
                     System.out.println(searchHit.getScore() + "---" + user);
                }
                return null;
            }
        });

//        /**
//         * 实现方式2： 非常简洁 。可以分页，但无法高亮
//         */
//        Iterator<UserDto> iter = userEsRepository.search(searchQuery).iterator();
//        while (iter.hasNext()) {
//
//            System.out.println(iter.next());
//        }

    }
}
