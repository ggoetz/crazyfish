//
//  AVViewController.m
//  Amplify
//
//  Created by Susan Tu on 4/20/14.
//  Copyright (c) 2014 Susan Tu. All rights reserved.
//

#import "AVViewController.h"
#import <MediaPlayer/MPVolumeView.h>

@interface AVViewController ()
@property (strong, nonatomic) IBOutlet UIView *mpViewController;
@property (weak, nonatomic) IBOutlet MPVolumeView *volumeSlider;
@end

@implementation AVViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
