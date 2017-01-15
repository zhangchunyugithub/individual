package model;

import java.io.Serializable;

/**
 * 结果对象
 * @author lei
 *
 */
public class Result implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2122034165453883917L;
	private Integer index;
	private Double value;
	
	
	public Result(Integer index, Double value) {
		super();
		this.index = index;
		this.value = value;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
}
