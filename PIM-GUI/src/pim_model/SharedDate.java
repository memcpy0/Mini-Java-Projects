package pim_model;

import java.time.LocalDate;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>接口名称：PIMEntity</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMTodo和PIMAppointment都要实现的接口
 * @author：张平
 */
public interface SharedDate {
	public LocalDate getDate();
	public void setDate(LocalDate date);
}

