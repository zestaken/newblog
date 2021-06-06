---
title: JQuery
date: 2021-01-21 17:35:45
tags: [JQuery, JS, 前端]
categories: 技术笔记
---
# JQuery概念

[参考文档](https://www.w3school.com.cn/jquery/index.asp)
* jQuery是一个快速、简洁的**JavaScript框架**，是继Prototype之后又一个优秀的JavaScript代码库（或JavaScript框架）。jQuery设计的宗旨是“write Less，Do More”，即倡导写更少的代码，做更多的事情。它封装JavaScript常用的功能代码，提供一种简便的JavaScript设计模式，优化**HTML文档操作、事件处理、动画设计和Ajax交互**。
* JavaScript框架：本质上就是一些**js文件**，这些文件中封装了js的原生代码。

# 快速入门

1. 下载JQuery
   1. 版本说明：
      1. 1.x：兼容ie678,使用最为广泛的，官方只做BUG维护，功能不再新增。因此一般项目来说，使用1.x版本就可以了，最终版本：1.12.4 (2016年5月20日)
      2. 2.x：不兼容ie678，很少有人使用，官方只做BUG维护，功能不再新增。如果不考虑兼容低版本的浏览器可以使用2.x，最终版本：2.2.4 (2016年5月20日)
      3. 3.x：不兼容ie678，只支持最新的浏览器。除非特殊要求，一般不会使用3.x版本的，很多老的jQuery插件不支持这个版本。目前该版本是官方主要更新维护的版本。最新版本：3.3.1（2018年1月20日） 
2. 导入JQuery的js文件：导入.min.js文件。
3. 使用

# JQuery对象和JS对象的区别与转换

* JQuery获取元素对象：`var $divs = $("div");`;
* JS获取元素对象：`var div2 = document.getElementsByTagName("div");`
* JQuery对象在操作时更加方便，但是**JQuery对象和JS对象的方法是不通用的**。
* JQuery对象和JS对象的互相转换：
  * JS-->JQ：`$(JS对象)`,如：`$(divs).html("修改html内容")`
  * JQ-->JS：`jq对象[索引]`或者`jq对象.get(索引)`,如：`$divs[1].innerHTML("修改HTML内容")`或者`$divs.get(0).innerHTML("修改HTML内容")`

# 基本语法

* 事件绑定：
  * 示例：获取b1按钮,绑定单击事件。
```javascript
$("#b1").click(function(){
   alert("click");
})
```
* 入口函数：dom文档加载完成后执行该函数中的代码
  * js写法：
```javascript
windows.onload = function(){
   alert("js");
}
```
   * jq写法：
```javascript
$(function(){
   alert("jq");
})
```
   * windows.onload与$(function(){})的区别：
     * windows.onload **只能定义一次**,如果定义多次，后边的会将前面的覆盖掉。
     * $(function(){})**可以定义多次**。
* 样式控制：css方法
  * 用css原本的样式名称：`$("#div1").css("background-color","red");`
  * 用dom的对象名称：`$("#div1").css("backgroundColor","pink");`

# 选择器

## 基本选择器

1. 标签选择器：（元素选择器）
   1. 语法：`$("html标签名")`,获得所有匹配标签名的元素;
2. id选择器：
   1. 语法：`$("#id的属性值")`,获得与指定id属性值匹配的元素;
3. 类选择器：
   1. 语法：`$(".class的属性值")`,获得与指定的class属性值匹配的元素;
* 并集选择器：
  * 语法：`$("选择器1,选择器2")`,获取多个选择器选中的所有元素。

## 层级选择器

1. 后代选择器：
   1. 语法：`$("选择器1 选择器2)`,选择选择器1选择的元素中满足选择器2的所有元素。（多层嵌套的也选择）
2. 子选择器：
   1. 语法：`$("选择器1 > 选择器2)`,选择选择器1选择的元素中满足选择器2的所有子元素。（多层嵌套的不选择)

## 属性选择器

1. 属性名称选择器：
   1. 语法：`$("标签名[属性名]")`,该标签下包含指定的属性的元素;
2. 属性选择器：
   1. 语法:`$("标签名[属性名 = '值']")`,该标签下**包含指定属性且该属性等于指定值**的元素。
   2. 语法:`$("标签名[属性名 != '值']")`,该标签下**不包含指定属性获知该属性不等于指定值**的元素。
   3. 语法:`$("标签名[属性名 ^= '值']")`,该标签下**包含指定属性且该属性值以指定值开始**的元素。
   4. 语法:`$("标签名[属性名 $= '值']")`,该标签下**包含指定属性且该属性值以指定值结尾**的元素。
   5. 语法:`$("标签名[属性名 *= '值']")`,该标签下**包含指定属性且该属性值中包含指定的值**的元素。
3. 复合属性选择器：
   1. 语法：`$("标签名[属性名 = '值'][][]...")`,该标签下，同时满足多个属性限定条件的元素。

## 过滤选择器

1. 首元素选择器：
   1. 语法：`:first`,获得选择的元素中的第一个元素，如`$(div:first)`。
2. 尾元素选择器;
   1. 语法：`:last`,获得选择的元素中的最后一个元素。
3. 非元素选择器：
   1. 语法：`：not(selector)`,获得选择的与元素中，不包括指定内容的元素。如：`$(div:not(.one))`，选择div中class不为one的元素。
4. 偶数选择器：
   1. 语法：`：even`,获得选择的元素中第偶数个的元素，从0开始计数。如;`$(div:even)`。
5. 奇数选择器：
   1. 语法：`：odd`,获得选择的元素中第奇数个的元素，从0开始计数。
6. 等于索引选择器：
   1. 语法：`:eq(index)`,获得选择的元素中第某位的元素。
7. 大于索引选择器：
   1. 语法：`:gt(index)`,获得选择的元素中索引大于指定索引的元素。
8. 小于索引选择器：
   1. 语法：`：lt(index)`,获得选择的元素中索引小于指定索引的元素。
9. 标题选择器：
   1. 语法：`:header`,获得标题元素，没有参数，固定的写法。

## 表单过滤选择器

1. 可用元素选择器：
   1. 语法：`：enabled`,获得可用元素;
2. 不可用元素选择器：
   1. 语法：`：disabled`,获得不可用元素;如：`$("input[type = 'text']:disabled").val("diabled")`，获取改变表单中不可用`<input>`元素的值。
3. 选中选择器1：
   1. 语法：`:checked`,获得单选/复选框中选中的元素。
4. 选中选择器2:
   1. 语法：`:selected`，获得下拉框中选中的元素。如:`$("#job > option :selected")`，获取下拉框选中的元素，若为多个，则相当于存在一个数组中。

# DOM操作

## 内容操作

1. `html()`:**获取/设置元素的标签体内容**,标签体内容包括该标签中嵌套的标签。
2. `text()`:获取/设置元素的标签体的**纯文本**内容。
3. `val()`:获取/设置元素的value属性值。
* 注：方法中不传参数，就是获取元素对应内容，方法中传递参数就是修改元素对应的内容。

## 属性操作

1. 通用属性操作：
   1. `attr()`:获取设置元素的属性,参数只有属性名则获取属性值，如果参数还有属性值则设置属性值。如：`$("#bj").attr("name")`和`$("#bj").attr("name","beijing")`
   2. removeAttr()`：删除属性。
   3. `prop()`:获取设置元素的属性;
   4. removeProp()`：删除属性。
   * attr和prop的区别：
     * 如果操作的是元素的**固有属性**,则建议使用prop;
     * 如果操作的是元素的**自定义属性**,则建议使用attr。
2. 对class属性的操作：
   1. `addClass()`:添加class属性值;
   2. `removeClass()`:删除class属性值，如：`removeClass("one")`,删除class="one"的属性。
   3. `toggleClass()`:切换class属性值;
      1. 如：`toggleClass("one")`:判断元素对象上存在class="one"，则将属性值one删除掉;如果元素对象上不存在class="one",则添加。
3. CRUD操作：
   1. `append()`:父元素将子元素追加到末尾。
      1. 对象1.append(对象2):将对象2**添加到对象1元素内部**,并且在末尾。（对象2会移动）
   2. `prepend()`:父元素将子元素追加到开头。
      1. 对象1.perpend(对象2）：将对象2**添加到对象1元素内部**,并且在开头。
   3. `appendTo()`:
      1. 对象1.appendTo(对象2):将对象1添加到对象2内部，并且在末尾。
   4. `prependTo()`:
      1. 对象1.prepend(对象2):将对象1添加到对象2内部，并且在开头。
   5. `after()`:添加元素到元素后边
      1. 对象1.after(对象2):将对象2添加到对象1后边，**对象1和对象2是兄弟关系**。
   6. `before()`:添加元素到元素前边
      1. 对象1.before(对象2):将对象2添加对象1前边，**对象1和对象2是兄弟关系**。
   7. `insertAfter()`:
      1. 对象1.insertAfter(对象2):将对象1添加到对象2后边，**对象1和对象2是兄弟关系**。
   8. `insertBefore()`:
      1. 对象1.insertBefore(对象2):将对象1添加到对象2前边，**对象1和对象2是兄弟关系**。
   9. `remove()`：移除元素
      1.  对象.remove():将对象删除掉;
   10. `empty()`:清空元素的所有后代元素。
       1.  对象.empty():将对象的后代元素全部清空，但是**保留当前对象以及其属性节点**。
   11. `clone()`:克隆元素并且选中这些克隆。


# JQuery动画

* 默认显示和隐藏方式：
  1. `show([speed], [easing],[fn])`:
     1. speed:动画的速度。三个预定义的值(slow,normal,fast)或表示动画时长的毫秒数值(如：1000);
     2. easing:用来指定切换效果。默认是swing,可用参数是linear;
        1. swing:动画执行时效果是，先慢，中间快，最后又慢。
        2. linear：动画执行是匀速的。
     3. fn:在动画执行结束时执行的函数，每个元素执行一次。
  2. `hide([speed], [easing],[fn])`;
  3. `toggle([speed], [easing],[fn])`:在show和hide之间切换。
* JQuery动画效果是同通过修改元素属性来实现的，如hide()就会设置的其中一个属性就是：`style="display:none"`

* 滑动显示和隐藏方式： 
  1. `slideDown([speed], [easing],[fn])`
  2. `slideUp([speed], [easing],[fn])`
  3. `slideToggle([speed], [easing],[fn])`
* 淡入淡出显示和隐藏方式：
  * `fadeIn([speed],[easing],[fn])`
  * `fadeOut([speed],[easing],[fn])`
  * `fadeToggle([speed],[easing],[fn])`
  
# JQuery遍历

* JS的遍历方式：`for(初始化值;循环结束条件;步长)`，也适用于JQ
* JQ的遍历方式：
  1. `jq对象.each(callback)`:callback是一个函数。
     1. 语法：`jq对象.each(function(index,element)[]{})`
        1. `index`:就是元素在集合中的索引
        2. `element`：就是集合中每一个元素对象。
        3. `this`:集合中的当前元素对象。
     2. 回调函数的返回值：
        1. `false`:如果当前的function返回为false，则结束循环。(break)
        2. `true`:如果当前的function返回为true,则结束本次循环，继续下次循环。（continue)
  2. `$.each(object, callback)`,其中callback是回调函数。
  3. `for...of:jq对象`:3.0版本后提供的方式
     1. 语法：`for（元素对象 of 容器对象)`.

# JQuery事件绑定

1. jquery标准事件绑定方式：`jq对象.事件方法（回调函数)`
   1. 如果调用事件方法，不传递回调函数，则会触发浏览器的默认行为。如：
      1. `jq对象.focus()`，使对象获得焦点;
      2. `表单对象.submit()`,使表单提交。
2. on绑定事件/off解除绑定：
   1. `jq对象.on("事件名称",回调函数)`;
   2. `jq对象.off("事件名称")`:
      1. 如果off方法不传递任何参数，则将组件上的所有事件全部解除。
3. 事件切换：toggle
   1. `jq对象.toggle(fn1,fn2,...)`:当单击jq对象对应的组件后，会执行fn1,第二次点击会执行fn2,依次循环往复。
   2. 注意：1.9版本后，toggle方法的事件切换功能被删除，jquery Migrate插件（js代码）可以恢复此功能。此js文件需要自行下载。
      1. `<script src="../js/jquery-migrate-1.0.0.js" type="text/javascript" charset="utf-8"></script>`

# JQuery插件

1. `$.fn.extend(object)`:
   1. 增强通过jquery获取的对象的功能。（即`$("选择器")`的功能)
2. `$.extend(object)`:
   1. 增强JQuery对象自身的功能。(即`$`自身的功能)
* 示例：
```javascript

$.extend({
   max:function(a,b) {
      return a >= b ? a : b;
   },
   min:function(a,b) {
      return a <= b ? a : b;
   }
})

$.max(2,3);

$.fn.extend({
   check:function(a,b) {
      alert("check");
   }
})

$("btn-check").check();
```

