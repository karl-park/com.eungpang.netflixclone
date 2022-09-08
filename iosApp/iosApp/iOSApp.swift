import SwiftUI
import shared

@main
struct iOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

	var body: some Scene {
		WindowGroup {
			ContentView(viewModel: ContentView.ViewModel())
		}
	}
}

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        startKoin()
        return true
    }
}