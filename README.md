自定义view：
基本套路：
对既有控件的组合；
完全自定义控件，集成View,SurfaceView,TextureView;

三类View
View：普通view，与宿主窗口共享一个绘图表面，再UI线程中绘制，有无硬件加速的情况下都能工作（没有硬件加速的情况下，canvas的部分方法会生效）；
SurfaceView:继承自View，绘制和显示效率高，拥有独立的绘图表面，UI在一个独立的线程中进行绘制，不占用主线程资源；需要结合surfaceHolder一起使用；
TextureView:集成自View，不创建单独的绘图表面，必须在硬件加速开启的窗口中才能正常工作；

自定义属性
   新建res/values/attrs.xml文件；
   添加自定义属性ID，属性名称和属性类型；
   再自定义view的带AttributeSet参数的构造方法里解析自定义属性值；
   在xml布局文件的跟标签或者需要使用自定义属性的标签中指定自定义属性的命名空间；
   在自定义view的布局中使用自定义属性，所有自定义属性的设置都是在指定的命名空间下，不能用Android这个命名空间；
注意点：
  TypedArray使用完成后一定要调用其recycle方法，否则会有内存泄露的问题；
  如果自定义View在一个单独的module中（不属于主工程），对attr的获取不能使用switch-case语句，要用if...else；

相关点


view的布局
测量：
重写onmeasure方法；
拿到mode和size；
调用setMeasuredDimension()方法

mode可选值：
EXACTLY：父控件告诉我们子控件了一个确定的大小，你就按这个大小来布局。比如我们指定了确定的dp值和macth_parent的情况。 
AT_MOST：当前控件不能超过一个固定的最大值，一般是wrap_content的情况。 
UNSPECIFIED:当前控件没有限制，要多大就有多大，这种情况很少出现。

onmeasure固定伪代码：
if mode is EXACTLY{
     父布局已经告诉了我们当前布局应该是多大的宽高, 所以我们直接返回从measureSpec中获取到的size 
}else{
     计算出希望的desiredSize
     if mode is AT_MOST
          返回desireSize和specSize当中的最小值
     else:
          返回计算出的desireSize
}

三大核心方法：
onmeasure：用于测量视图的大小；
onlayout：用于给视图进行布局；view的空方法，viewgroup的抽象方法；
ondraw：用于对视图进行绘制；