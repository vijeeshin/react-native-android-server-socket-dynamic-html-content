
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNAndroidServerSocketDynamicHtmlContentSpec.h"

@interface AndroidServerSocketDynamicHtmlContent : NSObject <NativeAndroidServerSocketDynamicHtmlContentSpec>
#else
#import <React/RCTBridgeModule.h>

@interface AndroidServerSocketDynamicHtmlContent : NSObject <RCTBridgeModule>
#endif

@end
