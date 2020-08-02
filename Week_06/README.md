# 12. 动态规划的实现及关键点

**分治+回溯+递归+动态规划**

它的本质就是将一个复杂的问题，分解成各种子问题，同时寻找它的重复性。不管是分治、回溯、递归还是动态规划，它们并没有本质上的非常大的不一样，很多时候就是一些小的细节问题。

理解动态规划之前先回顾一下“递归”、“分治”

**递归代码模版**
```
public void recur(int level, int param) {
    // terminator
    if (level > MAX_LEVEL) { 
      // process result
      return; 
    }
    // process current logic
    process(level, param);
    // drill down
    recur( level: level + 1, newParam);
    // restore current status
}
```
**分治代码模版**
```
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator
  if problem is None:
    print_result
    return
  # prepare data
  data = prepare_data(problem)
  subproblems = split_problem(problem, data)
    
  # conquer subproblems
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) ...
  
  # process and generate the final result
  result = process_result(subresult1, subresult2, subresult3, ...) 
  # revert the current level states
```

**感触**
1. 人肉递归低效、很累
2. 找到最近最简方法，将其拆解成可重复解决的问题
3. 数据归纳法思维（抵制人肉递归的诱惑）

**本质：寻找重复性 -> 计算机指令集**

## 动态规划 Dynamic Programming
1. Wiki定义：[https://en.wikipedia.org/wiki/Dynamic_programming](https://en.wikipedia.org/wiki/Dynamic_programming)
2. `“simplifying a complicated problem by breaking it down into simpler sub-problems”`（in a recursive manner）
   - 将一个复杂的问题，把它分解成简单的子问题

3. `Divide & Conquer + Option substructure` 分治 + 最优子结构

> 在Wiki里DP的定义它就明确指出，你需要进行分治，在这里可以看到，DP和分治它们是有内在联系的，它们并不少完全割裂的两个算法，那么它和分治很多时候本质的一个区别，就是上面第三点这里。一般来说动态规划的问题，它会是让你求一个最优解或者求一个最大值，或者求一个最少方式。正是因为它有所谓的这种最优子结构存在的话，那么你在中间的每一步的话就不需要把所有的状态都保存下来，你只需要存最优的状态，当然你还需要证明如果我每一步都存着相当于最优的值，最后的话我就能够推导出一个全局的最优的值。那么正是因为这样的话，就是引入了两个：一个就是有所谓的缓存了或者是说状态的存储数组，第二个的话就是在每一步的话都会把次优的状态给淘汰掉，只保留在这一步里面最优或者较优的一些状态来推导出最后的全局最优。

**关键点**
- 动态规划 和 递归或者分治 没有根本上的区别（关键看有无最优的子结构）
- *共性：找到重复子问题*
- 差异性：最优子结构、中途可以淘汰次优解

## 实战例题
### 实战例题一：斐波拉契数列
首先，斐波拉契数列的话，如果把“傻递归”弄出来的话，它的递归状态树如此，所以如果“傻递归”的话时间复杂度是指数级别的，如果在面试的时候一定不要直接说用递归就解决来这个问题了，因为慢的要死，而且是没必要的慢。那么它的程序的就是下面一块。
![](https://imgkr2.cn-bj.ufileos.com/bb916fca-4ccb-4039-9a82-58a8b7cbfd1f.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=FdXhxVUsXHiU3pYl5FOOYNMd1L8%253D&Expires=1596380179)

接下来我们再看它的状态树为什么是指数级的，很多人想不清楚，有些人会说是n平方的，其实不是。怎么分析它是指数级？就是它每一层的话都是指数级的结点：第一层一个结点fib(6)、第二层两个5和4、第三层这里有4个，每一层其实就乘二了，加在一起的话就是2的n次方，所以它是指数级的。
![](https://imgkr2.cn-bj.ufileos.com/f2280a53-2699-4bd6-9fc1-4d63df7fddb2.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=1IN68syCgzsFcWsODJEOk%252BUH3u0%253D&Expires=1596380398)

对于上面的代码可以简化：
```
// Java
int fib (int n) {
  return n <= 1? n : fib(n-1) + fib(n-2);
}
```
上面这个代码只是更加简洁清爽了，但并不改变时间复杂度，如果来改进时间复杂度？接下来就想讲一个就是加一个缓存，你可以存在数组里面，如果你用Pthon的你可以用`LRU Cache`之类的，那么这种方法就叫做**记忆化搜索**。
```
int fib (int n, int[] memo) {
  if (n <= 1) {
    return n;
  }  
  if (memo[n] == 0) {
    memo[n] = fib(n-1) + fib(n-2);
  }
  return memo[n];
}
```

![](https://imgkr2.cn-bj.ufileos.com/ea0b90c4-8dd7-44b4-9f02-713fc88b38c1.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=HVW9BMbCIJmp%252BGZEGA3lYsDbKiE%253D&Expires=1596381175)

在这里只要你写递归的话，后面你会发现，既然它的递归公式是这样，那么与其用一个递归再加记忆化搜索的这种形式，那么不如我们直接写一个循环，`for`的话就从最开始的初始值把它设好来之后，然后从第三个元素开始一直循环到最后我们要的，最后结果就在 `a[n]`里面，那么这个我们就叫做**“自底向上”**。

![](https://imgkr2.cn-bj.ufileos.com/9c106461-2f3a-4c9b-90b0-0477634e17a7.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=SNY1DgKhmuPOuwjPbWedi4%252FalSY%253D&Expires=1596381475)

### 实战例题二：路径计数
一个要从左上角这个点走到右下角去，每次的话他只能向右移动一步，或者向下他不能往左或者往上走，他只能向右走或者向下走，问最后走到`End`有多少张不同的走法，棋盘上黄色实心的表示障碍物。
![](https://imgkr2.cn-bj.ufileos.com/12d02dda-3b72-42be-b1d5-f176fbb8b7bd.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=iZAPNe%252BwNlxXxB8%252F%252FhZmbUu%252BDPg%253D&Expires=1596381822)

如果我们用所谓的分治的思想或者找重复性的思想应该怎么弄，其实很简单，大家开始的话可以想假设就没有障碍物，然后棋盘也没有那么大，假设棋盘在极端情况下是1x1的，走法只有一种对吧，如果是2x2的怎么办？更大的棋盘你怎么来解？其实就可以转化为这样的，如下：
![](https://imgkr2.cn-bj.ufileos.com/a5527ef2-01ff-4b58-b01f-987fc9afb452.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=5%252FNQSSe2u%252BXsdd%252FzNlCj8bmM3V8%253D&Expires=1596382161)

![](https://imgkr2.cn-bj.ufileos.com/d545a2ed-5227-4ae5-ab85-7aab4b381329.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=wUsqyIHrNIZ8nPvOWul8Q57M2dA%253D&Expires=1596382561)

**状态转移方程（DP方程）**

![](https://imgkr2.cn-bj.ufileos.com/f18e00e0-45ea-42da-87fb-f42751556040.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=bM9XIOSxW2bFw6cCnlUivEHyDQ8%253D&Expires=1596382522)

![](https://imgkr2.cn-bj.ufileos.com/fc2b5571-194c-4b5a-bcb7-ac84c7aa281b.png?UCloudPublicKey=TOKEN_8d8b72be-579a-4e83-bfd0-5f6ce1546f13&Signature=ZynjITlArYum2Xg%252FIHXJVdrDwaA%253D&Expires=1596382586)

**动态规划关键点**
1. 最优子结构 `opt[n]=best_of(opt[n-1], opt[n-2],...)`
2. 存储中间状态：`opt[i]`
3. 递推公式（美其名曰：状态转移方程或者DP方程）

    - Fib:opt[i]=opt[i-1]+opt[i-2] 
    -  二维路径：opt[i,j] = opt[i+1][j] + opt[i][j+1] (且判断a[i,j]是否空地)

 
![](https://upload-images.jianshu.io/upload_images/10170978-4b57485994039cb3.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    部分图片来源于网络，版权归原作者，侵删。