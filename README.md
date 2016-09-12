
## 这个project是怎么来的？

蘑菇街目前采用组件化的开发方式，一个app由很多个模块组成，每个模块都有单元测试的部分，然而有很多代码都是类似的。因此，为了减少重复劳动，我们花时间抽出来一个独立的project，专门做unit testing用的。

## 这个project是干什么用的？

如前所述，这个project里面主要是一些unit testing会用到的公共代码，来帮助你更快的做unit testing，减少一些boilerplate code。  
说白了就是一些帮助类，里面有些帮助方法。  
比如里面有个TestBase，里面有

```
// <=> 是等效于的意思
ae()   =>   Assert.assertEquals()
at()   =>   Assert.assertTrue()
af()   =>   Assert.assertFalse()
```

目前还没有完整的文档，要看都有哪些帮助类，哪些方法可以看源码哦，目前总共也没几个。  
总之，有了这个project，单元测试将变得更加的简单。

## 这个project用到那些框架/技术？
JUnit4, Robolectric，Mockito，AssertJ，Gson  
如果你不懂什么叫unit test，请看[这里](http://chriszou.com/2016/04/13/android-unit-testing-start-from-what.html)  
如果你不懂JUnit的使用，请看[这里](http://chriszou.com/2016/04/18/android-unit-testing-junit.html)   
如果你不懂Mockito的使用，请看[这里](http://chriszou.com/2016/04/29/android-unit-testing-mockito.html)  
如果你不懂Robolectric的使用，请看[这里](http://chriszou.com/2016/06/05/robolectric-android-on-jvm.html)  
有任何单元测试的问题，请看[这里](http://chriszou.com/) 


## 怎么样使用？
目前你可以使用http://jitpack.io/来引入这个项目

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.mogujie:natasha:v0.1.0'
}
```   
## 一些小例子

```java
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}

//测试纯Java代码，继承TestBase
public class StringUtilsTest extends TestBase {

    @Test
    public void should_isEmpty_works() {
        at(StringUtils.isEmpty(null));	//at() is short for Assert.assertTrue()
        at(StringUtils.isEmpty("")); 
        af(StringUtils.isEmpty("  "));  //af() is short for Assert.assertFalse()
        af(StringUtils.isEmpty("hello"));  
    }

}

//测试Android相关的代码，继承RobolectricTestBase，同时需要指定constants = BuildConfig.class，不然的话，会报资源找不到的错误
@Config( constants = BuildConfig.class )
public class ColorUtilsTest extends RobolectricTestBase {
    @Test
    public void should_parseColor_valid_color() {
        ae(Color.RED, Color.parseColor("#FF0000"));
        ae(Color.WHITE, Color.parseColor("#FFFFFF"));
    }
}
```

欢迎各种PR，建议以及吐槽！