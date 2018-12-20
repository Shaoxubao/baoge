package bigdata.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankSentence;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 摘要提取
 */
public class SummaryExtract {
    public static void main(String[] args) {
        String document =  "广州海格通信集团股份有限公司\n" +
                "关于中国证监会并购重组委员会审核公司发行股份及支付现金购买资产并募集配套资金暨关联交易事项的停牌公告\n" +
                "本公司及董事会全体成员保证公告内容真实、准确、完整，没有虚假记载、误导性陈述或重大遗漏。\n" +
                "广州海格通信集团股份有限公司（以下简称“公司”于2017年2月22日\n" +
                "晚接到中国证券监督管理委员会（以下简称“中国证监会”通知，中国证监会上市公司并购重组审核委员会将于近日召开工作会议，审核公司本次发行股份及支付现金购买资产并募集配套资金暨关联交易事项。\n" +
                "根据《上市公司重大资产重组管理办法》等规定，公司股票（股票简称：海格通信，股票代码：002465）自2017年2月23日（星期四）开市起停牌，待公司公告中国证监会上市公司并购重组审核委员会审核结果后复牌。\n" +
                "公司董事会提醒广大投资者注意投资风险。\n" +
                "特此公告。\n" +
                "广州海格通信集团股份有限公司\n" +
                "董事会\n" +
                "2017年2月23日";
        List<String> sentenceList2 = HanLP.extractSummary(document, 3);
        System.out.println(sentenceList2);

        List<String> sentenceList = spiltSentence(document);

        List<List<String>> docs = convertSentenceListToDocument(sentenceList);
        TextRankSentence textRank = new TextRankSentence(docs);
        int[] topSentence = textRank.getTopSentence(3);
        List<String> resultList = new LinkedList<String>();
        for (int i : topSentence) {
            resultList.add(sentenceList.get(i));
        }
        System.out.println(sentenceList);

    }

    /**
     * 将文章分割为句子
     *
     * @param document
     * @return
     */
    static List<String> spiltSentence(String document) {
        List<String> sentences = new ArrayList<String>();
        for (String line : document.split("[\r\n]")) {
            line = line.trim();
            if (line.length() == 0) continue;
            for (String sent : line.split("[，,。:：“”？?！!；;]")) {
                sent = sent.trim();
                if (sent.length() == 0) continue;
                sentences.add(sent);
            }
        }
        return sentences;
    }


    private static List<List<String>> convertSentenceListToDocument(List<String> sentenceList) {
        List<List<String>> docs = new ArrayList<List<String>>(sentenceList.size());
        for (String sentence : sentenceList) {
            List<Term> termList = StandardTokenizer.segment(sentence.toCharArray());
            List<String> wordList = new LinkedList<String>();
            for (Term term : termList) {
                if (CoreStopWordDictionary.shouldInclude(term)) {
                    wordList.add(term.word);
                }
            }
            docs.add(wordList);
        }
        return docs;
    }
}
