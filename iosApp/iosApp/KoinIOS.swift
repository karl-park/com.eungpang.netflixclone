import Foundation
import shared

func startKoin() {
    let doOnStartup = { NSLog("Hello from iOS/Swift!") }

    let koinApplication = KoinIOSKt.doInitKoinIOS(doOnStartUp: doOnStartup)
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}
