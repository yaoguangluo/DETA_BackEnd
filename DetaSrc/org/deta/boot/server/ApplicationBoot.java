package org.deta.boot.server;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;
import java.util.Random;
import org.deta.boot.thread.SocketThread;
import org.deta.boot.thread.SocketThreadPool;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.emotion.ortho.fhmm.imp.EmotionMapImp;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
public class ApplicationBoot {
	private static ServerSocket server;
	private static Properties properties;
	private Analyzer analyzer;
	private EmotionMap emotionMap;
	private int port;
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("src/main/resources/property.proterties")));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() {
		try {
			port = Integer.parseInt(properties.getProperty("port"));
			server = new ServerSocket(port);
			analyzer = new CogsBinaryForestAnalyzerImp();
			analyzer.init();
			emotionMap = new EmotionMapImp(); 
			emotionMap.initNegativeMap();
			emotionMap.initPositiveMap();
			emotionMap.initMotivationMap();
			emotionMap.initTrendingMap();
			emotionMap.initPredictionMap();
			emotionMap.initDistinctionMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		SocketThreadPool socketThreadPool = new SocketThreadPool();
		ApplicationBoot boot = new ApplicationBoot();
		boot.init();
		boot.addRestService();
		System.out.println("----德塔VPCS后端服务器----");
		while(true){
			if(socketThreadPool.getThreadsCount() < 300){
				SocketThread clientSocket = new SocketThread(boot.emotionMap, boot.analyzer, socketThreadPool, server.accept()
						, System.currentTimeMillis()+ "" + new Random().nextLong());
				socketThreadPool.addExecSocket(clientSocket.getSid(), clientSocket);
				clientSocket.start();
			}
		}
	}

	private void addRestService() {	
	}
}