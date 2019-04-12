package org.lyg.vpc.process.portImpl;
import sortProcessor.Quick_6D_luoyaoguang_Sort;

import org.deta.boot.server.ApplicationBoot;
import org.tinos.behavior.ICA.InitBehaviorICAKernel;
import org.tinos.behavior.test.SuccessICATest;
import org.tinos.emotion.engine.LenovoInit;
import org.tinos.emotion.estimation.EmotionSample;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.sensing.test.ANNTest;
import org.tinos.sensing.test.DNNTest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class RestNLPPortImpl{// implements RestLoginPort {
	public static Map<String, Object> dataWS(Analyzer analyzer, String string) throws IOException {
		System.out.println(string);
		List<String> sets = analyzer.parserString(string);
		Map<String, Object> output = new HashMap<>();
		String ws="";
		Iterator<String> iterator=sets.iterator();
		while(iterator.hasNext()) {
			ws += iterator.next()+" ";
		}
		output.put(URLEncoder.encode("ws","UTF-8"), URLEncoder.encode(ws,"UTF-8"));
		return output;
	}

	public static Map<String, Object> dataCX(Analyzer analyzer, String string) throws UnsupportedEncodingException {
		System.out.println(string);
		Map<String, String> pos = analyzer.getPosCnToCn();
		List<String> sets = analyzer.parserString(string);
		Iterator<String> iterator = sets.iterator();
		String cx = "";
		while(iterator.hasNext()) {
			String token = iterator.next();
			if(pos.containsKey(token)) {
				cx += token + "/"+pos.get(token)+" ";
			}
		}
		Map<String, Object> outputMap = new HashMap<>();
		outputMap.put(URLEncoder.encode("cx","UTF-8"), URLEncoder.encode(cx,"UTF-8"));
		return outputMap;
	}

	public static Map<String, Object> dataCY(Analyzer analyzer, String string) throws IOException {
		System.out.println(string);
		LenovoInit lenovoInit = new LenovoInit();
		lenovoInit.initExcludeAnalyzer(string, analyzer);	
		Map<String, EmotionSample> environmentSampleMap = lenovoInit.getEnvironmentInit().getEmotionSampleMap();
		Map<String, Object> lenovo = lenovoInit.getSensingMap().getLenovoMap();
		Map<String, Object> output = new HashMap<>();
		List<String> ciyi = new LinkedList<>();
		ciyi.add("\r\n环    境：");
		Iterator<String> Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				if(lenovo.containsKey(emotionSample.getDistinction())) {
					//System.out.print(lenovo.get(emotionSample.getDistinction()).toString()+" ");
					ciyi.add(lenovo.get(emotionSample.getDistinction()).toString()+" ");
				}else {
					//System.out.print(emotionSample.getDistinction()+" ");
					ciyi.add(emotionSample.getDistinction()+" ");
				}
			}
		}
		ciyi.add("\r\n动机联想：");
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				if(lenovo.containsKey(emotionSample.getMotivation())) {
					//System.out.print(lenovo.get(emotionSample.getMotivation()).toString()+" ");
					ciyi.add(lenovo.get(emotionSample.getMotivation()).toString()+" ");
				}else {
					//System.out.print(emotionSample.getMotivation()+" ");
					ciyi.add(emotionSample.getMotivation()+" ");
				}
			}
		}
		ciyi.add("\r\n倾向探索：");
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				if(lenovo.containsKey(emotionSample.getTrending())) {
					ciyi.add(lenovo.get(emotionSample.getTrending()).toString()+" ");
				}else {
					ciyi.add(emotionSample.getTrending()+" ");
				}
			}
		}
		ciyi.add("\r\n决策挖掘：");
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				if(lenovo.containsKey(emotionSample.getPrediction())) {
					ciyi.add(lenovo.get(emotionSample.getPrediction()).toString()+" ");
				}else {
					ciyi.add(emotionSample.getPrediction()+" ");
				}
			}
		}
		String cy = "";
		Iterator<String> iterator = ciyi.iterator();
		while(iterator.hasNext()) {
			cy += iterator.next() + " ";
		}
		output.put(URLEncoder.encode("cy", "UTF-8"), URLEncoder.encode(cy, "UTF-8"));
		return output;
	}

	public static Map<String, Object> dataCG(Analyzer analyzer, String string) throws InstantiationException, IllegalAccessException, IOException {
		System.out.println(string);
		DNNTest dNNTest = new DNNTest();
		ANNTest aNNTest = new ANNTest();
		String[][] ann = aNNTest.getANNMatrix(string, analyzer);
		String[][] dnn = dNNTest.getDNNMatrix(ann, analyzer, string);
		Map<String, Object> output = new HashMap<>();
		List<String> cigan = new LinkedList<>();
		Here:
		for(int i=0; i<dnn.length; i++) {
			double dnn_lwa = 0;
			if(null == dnn[i][3]) {
				continue Here;
			}
			dnn_lwa = Double.parseDouble(dnn[i][3]);
			if(dnn_lwa>0) {
				String line="";
				line+=ann[i][0] + ":";
				line+=dnn[i][3] + ":";
				cigan.add(line);
			}
		}
		String[][] value = new String[cigan.size()][2];
		Iterator<String> iterator = cigan.iterator();
		int count = 0;
		while(iterator.hasNext()) {
			String iteratorString = iterator.next();
			value[count][0] = iteratorString.split(":")[0];
			value[count++][1] = iteratorString.split(":")[1];
		}
		value = new Quick_6D_luoyaoguang_Sort().sort(value);
		String cg = "\r\n";
		for(int i = 0; i<value.length; i++) {
			cg += value[i][0] + ":" + value[i][1] + "\r\n";
		}
		output.put(URLEncoder.encode("cg", "UTF-8"), URLEncoder.encode(cg, "UTF-8"));
		return output;
	}

	public static Map<String, Object> dataCJ(EmotionMap emotionMap, Analyzer analyzer, String string) throws IOException {
		System.out.println(string);
		double[][] kernel = new double[1][];
		InitBehaviorICAKernel InitBehaviorICAKernel = new InitBehaviorICAKernel();
		kernel[0] = InitBehaviorICAKernel.getBehaviorICAKernel(string, analyzer, emotionMap);
		SuccessICATest successICATest=new SuccessICATest();
		successICATest.getKernelCNN(kernel);
		Map<String, Object> output = new HashMap<>();
		String cj = "\r\n";
		Iterator<String> iterator = InitBehaviorICAKernel.getForRestReturn().iterator();
		while(iterator.hasNext()) {
			cj += iterator.next() + "\r\n";
		}
		output.put(URLEncoder.encode("cj", "UTF-8"), URLEncoder.encode(cj, "UTF-8"));
		return output;
	}

	public static Map<String, Object> dataCL(Analyzer analyzer, String string) {
		ApplicationBoot.luoyaoguang.evolve();
		return null;
	}
}