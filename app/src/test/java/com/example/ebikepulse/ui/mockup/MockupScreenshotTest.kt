package com.example.ebikepulse.ui.mockup

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.resources.Density
import org.junit.Rule
import org.junit.Test

class MockupScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig(
            screenWidth = 1080,
            screenHeight = 2400,
            xdpi = 420,
            ydpi = 420,
            density = Density.XXHIGH,
        ),
        useDeviceResolution = true,
    )

    @Test
    fun home_mockup() {
        paparazzi.snapshot {
            MockupHomeMockup()
        }
    }

    @Test
    fun ride_recording_mockup() {
        paparazzi.snapshot {
            MockupRideMockup()
        }
    }

    @Test
    fun ride_report_mockup() {
        paparazzi.snapshot {
            MockupReportMockup()
        }
    }
}
