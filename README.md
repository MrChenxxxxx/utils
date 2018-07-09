# utilsProject
>这个项目是用来存工具类的

## 一、widget
>控件类

### 1、MoneyEditText
> 只能输入金额的eitdText
 
  用法：在配置文件里面引用

  	<com.skylele.myapplication.widget.MoneyEditText
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:hint="请输入金额"
          android:inputType="numberDecimal"/>

###2、BillItemView
>两个TextView并排的类

知识点：
 declare-styleable format="enum" 的使用
	
###3、 EmptyRecycleView
>带有空布局的RecycleView，不是真正的recycleView，如果要用到recycleView方法这里没有，可以新增方法进行透传

添加空布局的方法：

		1、此方法可以动态添加要显示的空布局
		public void setEmptyView(View emptyView)
		2、把空布局配置到erv_empty_view 这个属性中
		 <com.skylele.myapplication.widget.EmptyRecycleView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                cxg:erv_empty_view="@layout/view_empty"/>

###4、 popupWindow
    1、基本用法
    2、自定义popupWindow
    参考 https://www.cnblogs.com/jzyhywxz/p/7039503.html

	
## 二、util
> 工具类

### 1、SharePreferenceUtils
>包含基本数据类型存储和对象存储

	void putObject(String key, Object object) 复杂类型存<对象>
	<T> T getObject(String key, Class<T> clazz) 复杂类型取<对象>
### 2、CommonUtil
> 存放一些无家可归的工具类

	可以改变字体颜色、大小 通过配置style
	changeTextStyle(Context context, TextView textView, int start, int end, int style)