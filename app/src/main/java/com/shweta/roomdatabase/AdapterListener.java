package com.shweta.roomdatabase;

public interface AdapterListener {
    void onUpdate(PojoModel pojo);
    void  onDelete(double id,int pos);
}
