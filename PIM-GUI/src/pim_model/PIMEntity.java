package pim_model;
import java.io.Serializable;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMEntity</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：个人信息记录的抽象类
 * @author：张平
 */
public abstract class PIMEntity implements Serializable {
	String priority; // every kind of item has a priority
	String type;
	
    // default constructor sets priority to "normal"
    PIMEntity() {
    	priority = "Low Priority";
    }

    // priority can be established via this constructor.
    PIMEntity(String priority) {
    	this.priority = priority;
    }

    // accessor method for getting the priority string
    public String getPriority() {
        return priority;
    }
    // method that changes the priority string
    public void setPriority(String p) {
    	priority = p;
    }
    
    public String getType() {
    	return type;
    }
 
    abstract public String toString();
    
    abstract public boolean equals(PIMEntity p);
    
    // set current object value as p
    abstract public void setEntity(PIMEntity p);
}
