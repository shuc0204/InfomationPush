package com.info.service;

import com.info.model.Article;
import com.info.model.ArticleResultList;

import java.util.List;

/**
 * ͨ���ؼ����������½����service�ӿ�
 */

public interface ArticleSearchService {


    /**
     * ͨ���ؼ������������б�  ָ��Ҫ��ȡ������
     * @param keywords �ؼ�������
     * @param queryCount Ҫ��ȡ����������
     * @return ��ѯ����������б�
     */
    List<Article> searchArticleByKeyWord(List keywords, int queryCount);

    /**
     * ͨ���ؼ��ֻ�ȡ�����б�  ָ����ҳ������ʽ��ȡ
     * @param keyWordList  �ؼ�������
     * @param paggSize ҳ��С
     * @param curPage ��ǰҳ��
     * @return ��ѯ����������б� ����ҳ��Ϣ�Ķ���
     */
    ArticleResultList queryByKeyWords(List<String> keyWordList,int paggSize,int curPage);
}
