//
//  AVInput.h
//  Amplify
//
//  Created by Susan Tu on 4/21/14.
//  Copyright (c) 2014 Susan Tu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <AudioUnit/AudioUnit.h>
#include <AudioToolbox/AudioToolbox.h>


@interface AVInput : NSObject {
    AudioComponentInstance audioUnit;
    AudioBuffer tempBuffer; // this will hold the latest data from the microphone
}

@property (readonly) AudioComponentInstance audioUnit;
@property (readonly) AudioBuffer tempBuffer;

- (void) start;
- (void) stop;
- (void) processAudio: (AudioBufferList*) bufferList;

@end

// setup a global iosAudio variable, accessible everywhere
extern AVInput* iosAudio;
AVInput* iosAudio;