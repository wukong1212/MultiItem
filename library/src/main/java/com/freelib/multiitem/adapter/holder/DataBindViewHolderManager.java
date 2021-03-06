package com.freelib.multiitem.adapter.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 数据绑定ViewHolderManager
 * Created by free46000 on 2017/4/6.
 */
public class DataBindViewHolderManager<T> extends BaseViewHolderManager<T> {
    @LayoutRes
    private int itemLayoutId;
    private ItemBindView<T> itemBindView;

    {
        enableDataBind();
    }

    /**
     * @param itemLayoutId   item 布局文件资源id
     * @param bindVariableId 需要绑定一个VariableId时使用本构造函数
     */
    public DataBindViewHolderManager(@LayoutRes int itemLayoutId, int bindVariableId) {
        this(itemLayoutId, (dataBinding, data) -> dataBinding.setVariable(bindVariableId, data));
    }

    /**
     * @param itemLayoutId item 布局文件资源id
     * @param itemBindView item数据绑定回调 可以自定义绑定逻辑
     */
    public DataBindViewHolderManager(@LayoutRes int itemLayoutId, @NonNull ItemBindView<T> itemBindView) {
        this.itemLayoutId = itemLayoutId;
        this.itemBindView = itemBindView;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, T data) {
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        onBindViewHolder(dataBinding, data);
//        dataBinding.executePendingBindings();
    }

    /**
     * 绑定数据到视图 {@link ItemBindView}
     *
     * @param dataBinding item视图对应dataBinding类
     * @param data        数据源
     */
    protected void onBindViewHolder(ViewDataBinding dataBinding, T data) {
        itemBindView.onBindViewHolder(dataBinding, data);
    }

    @Override
    protected int getItemLayoutId() {
        return itemLayoutId;
    }


    /**
     * item数据绑定回调接口，在回调方法中自定义绑定逻辑
     */
    @FunctionalInterface
    public interface ItemBindView<T> {
        void onBindViewHolder(ViewDataBinding dataBinding, T data);
    }
}
