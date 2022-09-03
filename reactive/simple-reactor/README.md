## Project Reactor 核心概念
### Operators - Publisher / Subscriber
* **Nothing Happens Until You subscribe()** <br></br>
* **Flux [ 0..N ] - onNext()、onComplete()、onError()** <br></br>
* **Mono [ 0..1 ] - onNext()、onComplete()、onError()**


### Backpressure
* **Subscription** <br></br>
* **onRequest()、onCancel()、onDispose()**


### 线程调度 Schedulers
* **immediate() / single() / newSingle()** <br></br>
* **elastic() / parallel() / newParallel()**


### 错误处理
* **onError / onErrorReturn / onErrorResume** <br></br>
* **doOnError / doFinally**


### publishOn,subscribeOn 区别
```
简单说，两者的区别在于影响范围。
publishOn 影响在其之后的 operator 执行的线程池，而 subscribeOn 则会从源头影响整个执行过程。
所以，publishOn 的影响范围和它的位置有关，而 subscribeOn 的影响范围则和位置无关
```

***

## 参考链接
1. [https://github.com/labulakalia/ibm_bak/blob/main/ibm_articles/%E4%BD%BF%E7%94%A8Reactor%E8%BF%9B%E8%A1%8C%E5%8F%8D%E5%BA%94%E5%BC%8F%E7%BC%96%E7%A8%8B.md](https://github.com/labulakalia/ibm_bak/blob/main/ibm_articles/%E4%BD%BF%E7%94%A8Reactor%E8%BF%9B%E8%A1%8C%E5%8F%8D%E5%BA%94%E5%BC%8F%E7%BC%96%E7%A8%8B.md)
