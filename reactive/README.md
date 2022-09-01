## 响应式编程
### 定义
```
在计算中，响应式编程或反应式编程（英语：Reactive programming）是一种面向数据流和变化传播的声明式编程范式。
这意味着可以在编程语言中很方便地表达静态或动态的数据流，而相关的计算模型会自动将变化的值通过数据流进行传播
```

### 实例
```
在命令式编程环境中，a:=b+c表示将表达式的结果赋给a，而之后改变b或c的值不会影响a。
但在响应式编程中，a的值会随着b或c的更新而更新。电子表格程序就是响应式编程的一个例子。
单元格可以包含字面值或类似"=B1+C1"的公式，而包含公式的单元格的值会依据其他单元格的值的变化而变化
```

***


## Callback
### Example of Callback Hell
```
userService.getFavorites(userId, new Callback<List<String>>() {
  public void onSuccess(List<String> list) {
    if (list.isEmpty()) {
      suggestionService.getSuggestions(new Callback<List<Favorite>>() {
        public void onSuccess(List<Favorite> list) {
          UiUtils.submitOnUiThread(() -> {
            list.stream()
                .limit(5)
                .forEach(uiList::show);
            });
        }

        public void onError(Throwable error) {
          UiUtils.errorPopup(error);
        }
      });
    } else {
      list.stream()
          .limit(5)
          .forEach(favId -> favoriteService.getDetails(favId,
            new Callback<Favorite>() {
              public void onSuccess(Favorite details) {
                UiUtils.submitOnUiThread(() -> uiList.show(details));
              }

              public void onError(Throwable error) {
                UiUtils.errorPopup(error);
              }
            }
          ));
    }
  }

  public void onError(Throwable error) {
    UiUtils.errorPopup(error);
  }
});
```

### Example of Reactor code equivalent to callback code
```
userService.getFavorites(userId)
           .flatMap(favoriteService::getDetails)
           .switchIfEmpty(suggestionService.getSuggestions())
           .take(5)
           .publishOn(UiUtils.uiThreadScheduler())
           .subscribe(uiList::show, UiUtils::errorPopup);
```

### Example of Reactor code with timeout and fallback
```
userService.getFavorites(userId)
           .timeout(Duration.ofMillis(800))
           .onErrorResume(cacheService.cachedFavoritesFor(userId))
           .flatMap(favoriteService::getDetails)
           .switchIfEmpty(suggestionService.getSuggestions())
           .take(5)
           .publishOn(UiUtils.uiThreadScheduler())
           .subscribe(uiList::show, UiUtils::errorPopup);
```



***

## 参考链接
1. [https://projectreactor.io/](https://projectreactor.io/)
2. [https://github.com/reactor/reactor-core/blob/main/docs/asciidoc/reactiveProgramming.adoc](https://github.com/reactor/reactor-core/blob/main/docs/asciidoc/reactiveProgramming.adoc)
3. [https://github.com/labulakalia/ibm_bak/blob/main/ibm_articles/%E4%BD%BF%E7%94%A8Reactor%E8%BF%9B%E8%A1%8C%E5%8F%8D%E5%BA%94%E5%BC%8F%E7%BC%96%E7%A8%8B.md](https://github.com/labulakalia/ibm_bak/blob/main/ibm_articles/%E4%BD%BF%E7%94%A8Reactor%E8%BF%9B%E8%A1%8C%E5%8F%8D%E5%BA%94%E5%BC%8F%E7%BC%96%E7%A8%8B.md)