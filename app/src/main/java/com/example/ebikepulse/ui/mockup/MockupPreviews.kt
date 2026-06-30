package com.example.ebikepulse.ui.mockup

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/** Paparazzi `MockupScreenshotTest`와 동일: 1080×2400px @ 480dpi (XXHIGH). */
private const val MOCKUP_PREVIEW_DEVICE = "spec:width=1080px,height=2400px,dpi=480"

@Preview(name = "Home mockup", device = MOCKUP_PREVIEW_DEVICE)
@Composable
private fun MockupHomePreview() {
    MockupHomeMockup()
}

@Preview(name = "Ride mockup", device = MOCKUP_PREVIEW_DEVICE)
@Composable
private fun MockupRidePreview() {
    MockupRideMockup()
}

@Preview(name = "Report mockup", device = MOCKUP_PREVIEW_DEVICE)
@Composable
private fun MockupReportPreview() {
    MockupReportMockup()
}
