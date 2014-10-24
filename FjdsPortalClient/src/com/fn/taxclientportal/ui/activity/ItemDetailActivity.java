package com.fn.taxclientportal.ui.activity;

import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.fn.taxclientportal.ui.app.TaxConstants;

public class ItemDetailActivity extends TaxBasicActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail_layout);

		Intent intent = this.getIntent();

		String title = intent.getStringExtra(TaxConstants.Item.TITLE);
		String pulisDate = intent.getStringExtra(TaxConstants.Item.PULISH_DATE);

		aquery.id(R.id.title_textview).text(title);
		aquery.id(R.id.date_textview).text(pulisDate);
		WebView webView = aquery.id(R.id.content_webview).getWebView();
		webView.getSettings().setJavaScriptEnabled(true);
		String page = "<html><body><p style=\"text-align:left;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">今年</span><span style=\"font-family:\">6</span><span style=\"font-family:\">月起，罗源县地税局在受理税务方面率先引入二维码技术，大大缩短了企业和市民办理业务的等待时间，得到了市民的广泛好评。</span></p><p style=\"text-align:left;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">今年年初，罗源县内一家房地产公司向地税部门反映企业在申报契税时程序过于复杂、耗时过长等涉税诉求，而该房地产项目所拥有的</span><span style=\"font-family:\">3.5</span><span style=\"font-family:\">万套增量房，依照原先的人工办证流程，需要近</span><span style=\"font-family:\">300</span><span style=\"font-family:\">个工作日才能办结。对此，罗源县地税局通过联合第三方信息技术公司，开发出了一套以契税申报为载体、二维码技术为核心的涉税申报信息采集系统。</span></p><p style=\"text-align:left;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">市民可通过登录该系统，并根据所办业务事先填报涉税必备的数据信息，获取专有的二维码。在窗口手续办理涉税时，市民只需要将此二维码提交给受理窗口的工作人员，依照规定提交部分纸质资料，即可快速办结涉税手续。</span></p><p style=\"text-align:left;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">同时，该系统也能通过抓取企业销售系统、税务开票系统及产权登记报备系统中的有效信息，组建相应的企业数据库。经办人员只需要在采集端输入房产编码，系统将会自动调取相关涉税信息，迅速完成信息采集工作。</span></p><p style=\"text-align:left;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">据统计，该系统上线试运行以来，共完成</span><span style=\"font-family:\">800</span><span style=\"font-family:\">余笔业务，平均办理时间为</span><span style=\"font-family:\">6</span><span style=\"font-family:\">分钟，相应缩短</span><span style=\"font-family:\">60%</span><span style=\"font-family:\">，至今尚未出现任何数据差错。</span></p><p style=\"text-align:center;line-height:27px;text-indent:0em;background:white\"><span style=\"font-family:\"><img title=\"20141022-150826-罗源局引入二维码 提高办税效率1\" src=\"http://www.fj-l-tax.gov.cn/filecenter/res_base/cms.war/application/2014/10/22/5447584418ec493eb2604bd2.jpg\" /></span></p><p style=\"text-align:center;line-height:27px;text-indent:2em;background:white\"><span style=\"font-family:\">图为：<span style=\"font-family:\">税务工作人员为纳税人讲解二维码申报表</span></span></p><p>&nbsp;</p></body></html>";
		webView.loadDataWithBaseURL("x-data://base", page,
				TaxConstants.Web.CONTENT_TYPE,
				TaxConstants.Web.DEFAULT_ENCODING, null);
	}
}
