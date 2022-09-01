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
