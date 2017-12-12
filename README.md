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