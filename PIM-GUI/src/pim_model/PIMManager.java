package pim_model;

import java.util.*;
import java.io.*;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMManager</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMEntity列表的管理类,有序列化存储和反序列化存储功能
 * @author：张平
 */
public class PIMManager {
	private String dataFilePath = "PIMDatabase.dat";
	private File dataFile = null;
	private ArrayList<PIMEntity> itemList;
	
	public PIMManager() throws IOException {
		dataFile = new File(dataFilePath);
		itemList = new ArrayList<>();
		if (!dataFile.exists()) { // 如果数据文件不存在
			dataFile.createNewFile(); // 新建一个数据文件
		} else loadData(); // 构造时行为,如果数据文件存在,则加载数据
	}
	 
	public String getDataFilePath() {
		return dataFilePath;
	}
	
	public void setDataFilePath(String other) throws IOException {
		dataFilePath = other;
		dataFile = new File(dataFilePath);
		if (!dataFile.exists()) { // 如果数据文件不存在
			dataFile.createNewFile(); // 新建一个数据文件
		}
	}
	
	public void addPIMEntity(PIMEntity p) {
		itemList.add(p);
	}
	
	public ArrayList<PIMEntity> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<PIMEntity> pl) {
		itemList = pl;
	}
	
	// 将itemList对象数组中的对象保存到文件中
	public void saveData() {
		if (dataFile.canWrite()) { // 可写文件
			try (					
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
			) {
				oos.writeObject(itemList); // 序列化集合对象
				oos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 对数据文件反序列化,重新生成对象数组itemList
	public void loadData() {
		if (dataFile.canRead() && dataFile.length() > 0) { // 可读文件,长度大于0时读取
			try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile));
			) {
				itemList = (ArrayList<PIMEntity>)ois.readObject(); // 将对象反序列化
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}