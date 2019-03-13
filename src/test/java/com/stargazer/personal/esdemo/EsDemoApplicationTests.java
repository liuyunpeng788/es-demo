package com.stargazer.personal.esdemo;

import com.stargazer.personal.esdemo.dto.UserDto;
import com.stargazer.personal.esdemo.service.EsBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.stargazer.personal.esdemo.util.CommonUtils.getUrlSafeBase64String;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDemoApplicationTests {

    @Autowired
    EsBaseService esBaseService;
    @Test
    public void findUserInfo() {
        String name = "王麻子";
        List<UserDto> list  = esBaseService.findByName(name);
        list.forEach(System.out::print);
    }


    @Test
    public void saveUserInfo() throws Exception {
        List<UserDto> list = new ArrayList<>(3);
        String str1 = "王志刚称，过去一年，科技事业在建设创新型国家历史征程上又迈出了坚实的步伐，涌现出一批以体细胞克隆猴、散裂中子源为代表的重大原创成果，港珠澳大桥、高铁、5G等一大批重大科技攻关为国家经济社会发展提供了新的动力。雾霾防治、肿瘤重大诊疗设备、原创抗阿尔茨海默症新药等一批先进技术应用不断提升民生福祉。同时，改进项目评审、机构评估、人才评价，破除“四唯”，扩大科技人员自主权，为科研人员松绑减负等一批务实改革举措落地生效。\n" +
                "\n" +  "　　鼓励社会各界参与基础研究\n" +                "\n" +
                "　　王志刚表示，对中国科技界来说，基础研究的能力和产出是一个短板。目标导向和问题导向促使我们必须把基础研究作为一个重点，在整个科技创新的总布局中要着重布局，这是整个科技界的呼声，也是我们整个国家战略在科技方面的重点，是我们必须做好的。";
        String str2 = "大家经常拿中美基础研究投入做对比，美国基础研究投入占其研发经费的比重在15%左右，而我国长期徘徊在5%左右。就基础研究经费投入来说，美国是联邦政府、地方政府、企业、社会力量都在投，中国基本上是中央财政投的，地方财政和企业投得很少。科技部将思考、研究如何动员企业、社会各界重视、投入、参与基础研究，并提出建议。\n" +
                "\n" +
                "　　王志刚表示，去年国务院专门出台了加强基础研究的意见，这是新中国以国务院文件形式第一次就加强基础研究作出全面部署。这说明中国已经到了高质量发展阶段，要建立现代化经济体系，更多依靠创新驱动，把科技摆在核心位置，基础研究的地位就显得更加突出";
        String str3 = "王志刚说，政府工作报告中提出，在基础研究领域进行一些包干制试点。科研管理特别是经费管理，要信任广大科研人员。“包干制”已选了60多家科研单位试点。要征求科技界大家的意见，形成试点方案，不断推进、不断完善，最后变成科研管理和经费管理的方法，让科研人员有更大的自由度，更大的获得感。\n" +
                "\n" +
                "　　要把包干制跟“放管服”结合起来。它是一种“放”，但是“放”不等于不管，只是管的方式、管的理念会发生变化。所以，在这一点上，政府也要研究，科技部当然要优先研究这件事情。";
        String str4 = "此外，关于医保目录调整等热点问题，吴以芳也给出了自己的建议。首先，他表示，建议医保目录的调整最好每年进行一次，因为五年以上进行一次调整，影响了药物的可及性。在上一次的目录调整中，我们看到部分地区到现在还没有执行，按道理讲国家医保目录调整以后各省应该迅速跟进到位，平行同步执行。因此，他希望国家的政策能够及时传递给老百姓，使老百姓真正感受到性格感和获得感。";
        String str5 = " 在2019声音·责任医药界人大代表政协委员座谈会上，复星医药总裁兼首席执行官吴以芳表示，国家相关部门在政策上进一步支持加快药品的审评审批，对于医药行业来说意义非凡，但是在此基础上，我们可以尝试进行更深层次的改革。例如，开展稳定性试验的时候，同步进行审评审批，数据后期补充提交；允许在更大的范围内对新药注册的申报准许“滚动式”提交；对有突破性疗效的新药实现有条件批准等等。对于审批制度和流程也可以合理地将部分审批改为备案和平行审查，优化和简化相关程序和要求。这样对创新研发也是一种鼓励，也会大大提高效率。";
        String str6 = "大家经常拿中美基础研究投入做对比，美国基础研究投入占其研发经费的比重在15%左右，而我国长期徘徊在5%左右。就基础研究经费投入来说，美国是联邦政府、地方政府、企业、社会力量都在投，中国基本上是中央财政投的，地方财政和企业投得很少。科技部将思考、研究如何动员企业、社会各界重视、投入、参与基础研究，并提出建议。\n" +
                "\n" +
                "　　王志刚表示，去年国务院专门出台了加强基础研究的意见，这是新中国以国务院文件形式第一次就加强基础研究作出全面部署。这说明中国已经到了高质量发展阶段，要建立现代化经济体系，更多依靠创新驱动，把科技摆在核心位置，基础研究的地位就显得更加突出";
        String str7 = "3月2日，2019EBC易贸生物产业大会在南京盛大拉开帷幕。本届大会由2019（第九届）抗体药物及新药研发高峰会、2019（第七届）分子诊断产业高峰论坛、2019（第五届）细胞免疫治疗产业高峰论坛、2019（第二届）易企说投融资领袖论坛等四大论坛及二十余场特色活动构成。大会聚焦精准医疗，深入探讨诊断、治疗、用药全产业链，融合政策导向、市场投资、技术研发、临床应用等热门话题。此外，EBC的亮点在于开展论坛会议的同时，举办生物产业展览，形成专业论坛与行业展览的多方联动结合，以会带展，以展促会。";
        String str8 = "2019EBC易贸生物产业大会盛大召开！\n" +
                "基因谷  \n" +
                "\n" +
                "\n" +
                "3月2日，2019EBC易贸生物产业大会在南京盛大拉开帷幕。本届大会由2019（第九届）抗体药物及新药研发高峰会、2019（第七届）分子诊断产业高峰论坛、2019（第五届）细胞免疫治疗产业高峰论坛、2019（第二届）易企说投融资领袖论坛等四大论坛及二十余场特色活动构成。大会聚焦精准医疗，深入探讨诊断、治疗、用药全产业链，融合政策导向、市场投资、技术研发、临床应用等热门话题。此外，EBC的亮点在于开展论坛会议的同时，举办生物产业展览，形成专业论坛与行业展览的多方联动结合，以会带展，以展促会。\n" +
                "\n" +
                "\n" +
                "\n" +
                "大会开始由上海易贸商务发展有限公司总经理叶登峰、特邀嘉宾江苏省药品监督管理局局长王越致开幕词，预祝本届大会成功召开并对到场嘉宾表示诚挚欢迎。\n" +
                "\n" +
                "\n" +
                "上海易贸商务发展有限公司总经理叶登峰致辞\n" +
                "\n" +
                "\n" +
                "江苏省药品监督管理局局长王越致辞\n" +
                "\n" +
                "特邀中国工程院院士陈志南作“免疫治疗2.0时代”主题报告，中国工程院院士王红阳作“肿瘤精准诊疗新挑战”主题报告。\n" +
                "\n" +
                "\n" +
                "中国工程院院士陈志南作主题发言\n" +
                "\n" +
                "\n" +
                "中国工程院院士王红阳作主题发言\n" +
                "\n" +
                "\n" +
                "\n" +
                "专场访谈\n" +
                "\n" +
                "专场访谈“2030中国创造，如何实现！”由BCG合伙人兼董事总经理吴淳主持，复星医药CEO吴以芳、百奥财富董事长杨志、江苏省药品监督管理局局长王越共同参与。\n" +
                "\n" +
                "\n" +
                "展区\n" +
                "\n" +
                "本届EBC特色在于把会议和展览相结合，为医药医疗行业的企业家、研究人员、投资家等提供多维度的合作交流平台。现场共有来自全国各地的生物药企和机构百余家，前来参会的嘉宾不仅能够接收到最前沿的资讯，还能够直接到展区和感兴趣的企业直接交流沟通，洽谈合作。\n" +
                "\n" +
                "\n" +
                "\n" +
                "展区人流涌动，交流互动精彩纷呈。很多参展企业带着自家的最新科技产品亮相本次展会，吸引了不少参会人员驻足咨询交流。\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "此外，本届EBC大会还吸引了浙江电视台、江苏电视台、第一财经、易企说、小桔灯网等几十家媒体到场采访。\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "四大分会&特色专场\n" +
                "\n" +
                "\n" +
                "第九届抗体药物及新药研发高峰会、第七届分子诊断产业高峰论坛、第五届细胞免疫治疗产业高峰论坛、第二届易企说投融资领袖论坛四大分论坛于3月2日下午开始，3月3日全天持续进行。此外，本次大会还特设了伴随诊断与个体化医疗专场、肿瘤诊断与治疗专场、项目合作路演、展望生物产业营销四大特色专场。EBC会场内激烈讨论的氛围高涨，各会场座无虚席。\n" +
                "\n" +
                "\n" +
                "第九届抗体药物及新药研发高峰会共有“仰望星空——抗体药物研发进展”、“生物药注册与申报”、“合作驱动产业共进”、“生物药临床策略”、“创新技术推进药物开发”五个专场，主要围绕生物药研发、抗体药物疗法、创新技术等热点话题进行探讨。\n" +
                "\n" +
                "\n" +
                "第七届分子诊断产业高峰论坛共有“分子诊断企业发展策略”、“消费级基因检测——市场格局与渠道策略”、“体外分子诊断产品的开发与应用”、“注册申报策略与方法”、“基因检测产品研发与应用”、“分子诊断前沿技术开发进展”六个专场，主要围绕中国分子诊断产业的现状、DTC产品的推广、产品的开发应用等内容展开。\n" +
                "\n" +
                "\n" +
                "第五届细胞免疫治疗产业高峰论坛共有“研发与临床一CAR-T进展”、“临床WORKSHOP”、“研发与临床——前沿近况”、“细胞制品质量与产业化”四个专场，CAR-T细胞治疗、CRO临床研究、精准免疫治疗，成为了本次分论坛的热门讨论话题。\n" +
                "\n" +
                "\n" +
                "第二届易企说投融资领袖论坛共有“投资募资，案例复盘”、“行业梳理，投资经验”、“融资路演，资本链接”三个分论坛板块构成，主要围绕医药医疗投融资领域企业比较关注的投融资大环境、细分跑道、融资路演等内容展开。\n" +
                "\n" +
                "2019易贸生物产业大会晚宴暨评选颁奖晚宴\n" +
                "\n" +
                "\n" +
                "\n" +
                "为了鼓励辛勤耕耘在生物产业有创新精神的行业领袖和企业代表，发掘生物产业的创新力量表彰引领时代的优秀标杆，2018年末，易贸医疗特发起“2018易贸生物产业年度评选”。在经过企业自主提名、网络投票和专家投票之后，2018易贸生物产业年度评选结果于2019易贸生物产业大会晚宴上正式公布。\n" +
                "\n" +
                "\n" +
                "\uD83D\uDC51 2018新锐投资机构\n" +
                "\n" +
                "博远嘉泰（湖北）股权投资管理有限公司（博远资本）\n" +
                "\n" +
                "约印大通（天津）资产管理有限公司（约印医疗基金）\n" +
                "\n" +
                "苏州工业园区元生创业投资管理有限公司（元生创投）\n" +
                "\n" +
                "\uD83D\uDC51 2018生物产业年度企业家\n" +
                "\n" +
                "江苏康宁杰瑞生物制药有限公司董事长兼总裁徐霆\n" +
                "\n" +
                "\uD83D\uDC51 2018生物产业最具影响力企业\n" +
                "\n" +
                "再鼎医药（上海）有限公司\n" +
                "\n" +
                "\uD83D\uDC51 2018生物产业创新突破企业\n" +
                "\n" +
                "凯杰（苏州）转化医学研究有限公司\n" +
                "\n" +
                "上海鹍远生物技术有限公司\n" +
                "\n" +
                "天境生物科技（上海）有限公司\n" +
                "\n" +
                "\uD83D\uDC51 2018生命科学服务商\n" +
                "\n" +
                "\n" +
                "\n" +
                "南京金斯瑞生物科技有限公司\n" +
                "\n" +
                "上海睿智化学研究有限公司\n" +
                "\n" +
                "中美冠科生物技术有限公司”\n" +
                "\n" +
                "\n" +
                "两天的时间竟如此短暂，2019第四届易贸生物产业大会也随着四大分论坛和专场发言的结束圆满拉下了帷幕。两天内，来自体外诊断、细胞免疫、抗体药物、医药投融资等生物医药全产业领域的参会人员达两千多人次，参展公司共计百余家。易贸生物产业大会作为极具规模和深度的专业大会，以会议和展览相结合的新形式为生物医药全产业链的企业和机构搭建学术交流和商务合作的专业平台。来年将会有更多的惊喜值得期待！EBC，明年再见！";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.CHINA);

        list.add(new UserDto().builder(getUrlSafeBase64String(), "孙七1",31, dateFormat.parse("1990-02-01T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "张三2", 43, dateFormat.parse("1990-02-02T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "李四3", 34, dateFormat.parse("1990-02-03T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "王二4", 51, dateFormat.parse("1990-02-04T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "吴六5", 22, dateFormat.parse("1990-02-05T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "袁五6", 38, dateFormat.parse("1990-02-06T12:00:01.001+0800"),str8));
        list.add(new UserDto().builder(getUrlSafeBase64String(), "刘一7", 41, dateFormat.parse("1990-02-07T12:00:01.001+0800"),str8));


        esBaseService.putAll(list);
    }

    @Test
    public void delTest(){
        esBaseService.deleteAll();
    }

    @Test
    public void queryTest(){
        String strQuery = "EBC";
       esBaseService.search(strQuery);
    }

    @Test
    public void queryMultiTermTest(){
        String strQuery = "EBC,第九届+吴以芳";
        esBaseService.searchMultiTerm(strQuery);
    }

    @Test
    public void testHighLight(){
        String strQuery = "吴以芳";
        esBaseService.highLightTermsQuery(strQuery);
    }

}
