## BasePermissions

一行代码实现封装动态权限申请

![Release](https://jitpack.io/v/ymkiux/permissions.svg) ![Apache Licence](http://img.shields.io/badge/license-Apache2.0-ff6600.svg)

#### 引入

```css
implementation 'com.github.ymkiux:permissions:0.0.01'
```
#### 简单使用

```
Permissions.with(requireActivity())
        .request(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        .OnAllowCall(object : AllowCallback {
            override fun allOwDoWork() {
                //do related operations
            }
        })
        .build()
```

#### 尾言

> 如果觉得还不错,期待你的star！

