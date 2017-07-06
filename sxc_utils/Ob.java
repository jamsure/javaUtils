package com.xz.base.utils;

import java.util.ArrayList;

import com.xz.base.domain.BaseEntity;
import com.xz.base.model.OrderByModel;

public class Ob <T extends BaseEntity>{
	
	private T t;
	
	public Ob(T e){
		t = e;
		if(t.getOrderByModelList()==null){
			t.setOrderByModelList(new ArrayList<OrderByModel>());
		}
		t.setOb_type("custom");
	}
	
	public T fetchEntity(){
		return t;
	}
	
	public void clear(){
		t = null;
	}
	
	public void resetEntity(T f){
		t = f;
		if(t.getOrderByModelList()==null){
			t.setOrderByModelList(new ArrayList<OrderByModel>());
		}
		t.setOb_type("custom");
	}
	
	public void originalEntity(){
		t.getOrderByModelList().clear();
		t.setOb_type("default");
	}
	
	public Ob<T> orderBy(String field,boolean... args){
		for(OrderByModel tempOrderByModel:t.getOrderByModelList()){
			if(field.equals(tempOrderByModel.getOrderByField())){
				t.getOrderByModelList().remove(tempOrderByModel);
				break;
			}
		}
		boolean is_asc = true;
		if(args.length>0){
			is_asc = args[0];
		}
		t.getOrderByModelList().add(new OrderByModel(field,is_asc?"asc":"desc"));
		return this;
	}
	
	public Ob<T> asc(String field){
		for(OrderByModel tempOrderByModel:t.getOrderByModelList()){
			if(field.equals(tempOrderByModel.getOrderByField())){
				t.getOrderByModelList().remove(tempOrderByModel);
				break;
			}
		}
		t.getOrderByModelList().add(new OrderByModel(field,"asc"));
		return this;
	}
	
	public Ob<T> desc(String field){
		for(OrderByModel tempOrderByModel:t.getOrderByModelList()){
			if(field.equals(tempOrderByModel.getOrderByField())){
				t.getOrderByModelList().remove(tempOrderByModel);
				break;
			}
		}
		t.getOrderByModelList().add(new OrderByModel(field,"desc"));
		return this;
	}

}
