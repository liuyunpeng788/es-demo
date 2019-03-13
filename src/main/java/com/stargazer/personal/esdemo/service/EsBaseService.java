package com.stargazer.personal.esdemo.service;

import com.stargazer.personal.esdemo.dto.UserDto;
import com.stargazer.personal.esdemo.repository.UserEsRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

import static com.stargazer.personal.esdemo.constant.Constant.*;

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
//        list.forEach(x->userEsRepository.save(x));
    }

    @Transactional
    public void deleteAll() {
        userEsRepository.deleteAll();
    }

    public void search(String strQuery) {
        QueryBuilder builder = new MatchPhraseQueryBuilder("description", strQuery);
        Iterator<UserDto> iter = userEsRepository.search(builder).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
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
     * @return
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

//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;

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
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0,10, new Sort(Sort.Direction.DESC,"age","birthday"))); // sort 支持多个字段排序
        nativeSearchQueryBuilder.withQuery(queryBuilders);
        nativeSearchQueryBuilder.withHighlightFields(new HighlightBuilder.Field("description").preTags(preTag).postTags(postTag));
        SearchQuery searchQuery = nativeSearchQueryBuilder.build();
        /**
         * 实现方式1：较复杂
         */

//        elasticsearchTemplate.queryForPage(searchQuery, UserDto.class, new SearchResultMapper() {
//            @Override
//            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
//                for (SearchHit searchHit : response.getHits()) {
//                    if (response.getHits().getHits().length <= 0) {
//                        return null;
//                    }
//                    UserDto user = new UserDto();
//                    user.setId(searchHit.getId());
//
//                    user.setBirthday(parseEsDate((String)searchHit.getSourceAsMap().get("birthday")));
//                    user.setName((String)searchHit.getSourceAsMap().get("name"));
//                    user.setAge((Integer) searchHit.getSourceAsMap().getOrDefault("age",0));
//                    user.setDescription(searchHit.getHighlightFields().get("description").fragments()[0].toString());
//                     System.out.println(user);
//                }
//                return null;
//            }
//        });

        /**
         * 实现方式2： 非常简洁
         */
        Iterator<UserDto> iter = userEsRepository.search(searchQuery).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}
