package com.myorg;

import software.amazon.awscdk.core.App;

import java.util.Arrays;

public class CdkLunchNLearnApp {
    public static void main(final String[] args) {
        App app = new App();

        new CdkLunchNLearnStack(app, "CdkLunchNLearnStack");

        app.synth();
    }
}
