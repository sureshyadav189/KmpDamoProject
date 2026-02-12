import SwiftUI
import Shared
@main
struct iOSApp: App {

    init(){
        AppDISetupKt.doInitKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}