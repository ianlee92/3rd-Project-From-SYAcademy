package com.sist.web;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

@Component
public class RManager_park{
	public void graph(int no){
		try {
			RConnection rc = new RConnection();
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			rc.voidEval("library(wordcloud)");
			rc.voidEval("png(\"C:/springDev/springStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/3rdTeamProject/naverpark"+no+".png\")");
			rc.voidEval("txt<-readLines(\"c:/upload/naverpark.txt\", encoding = \"UTF-8\")");
			rc.voidEval("txt1<-sapply(txt, extractNoun, USE.NAMES = F)");
			rc.voidEval("txt2<-unlist(txt1)");
			rc.voidEval("txt3<-Filter(function(x){nchar(x)>=2}, txt2)");
			rc.voidEval("txt4<-table(txt3)");
			rc.voidEval("txt5<-head(sort(txt4, decreasing=T), 100)");
			rc.voidEval("wordcloud(names(txt5), freq = txt5, min.freq = 3, max.words = 100, random.order = F, scale = c(10,1), colors = rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}