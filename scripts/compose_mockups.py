"""Compose mockup export: Paparazzi snapshots → README PNGs + overview.

Workflow (full regen):
  ./gradlew :app:recordPaparazziDebug
  python scripts/compose_mockups.py

Overview only (docs PNGs already present):
  python scripts/compose_mockups.py

Snapshots are gitignored; canonical mockups live in docs/images/mockups/.

See docs/images/mockups/mockup-spec.md
"""

from __future__ import annotations

import shutil
from pathlib import Path

from PIL import Image

REPO_ROOT = Path(__file__).resolve().parents[1]
SNAPSHOT_DIR = REPO_ROOT / "app" / "src" / "test" / "snapshots" / "images"
OUT_DIR = REPO_ROOT / "docs" / "images" / "mockups"

CANVAS_W, CANVAS_H = 1080, 2400

SNAPSHOT_TO_OUTPUT = {
    "com.example.ebikepulse.ui.mockup_MockupScreenshotTest_home_mockup.png": "home.png",
    "com.example.ebikepulse.ui.mockup_MockupScreenshotTest_ride_recording_mockup.png": "ride-recording.png",
    "com.example.ebikepulse.ui.mockup_MockupScreenshotTest_ride_report_mockup.png": "ride-report.png",
}

try:
    RESAMPLE = Image.Resampling.LANCZOS
except AttributeError:
    RESAMPLE = Image.LANCZOS


def export_snapshots() -> list[Image.Image]:
    """Refresh docs mockups from Paparazzi snapshots when present; else use existing docs PNGs."""
    OUT_DIR.mkdir(parents=True, exist_ok=True)
    exported: list[Image.Image] = []

    for src_name, dst_name in SNAPSHOT_TO_OUTPUT.items():
        src = SNAPSHOT_DIR / src_name
        dst = OUT_DIR / dst_name

        if src.exists():
            shutil.copy2(src, dst)
            print(f"copied snapshot → {dst_name}")
        elif not dst.exists():
            raise FileNotFoundError(
                f"Missing {dst_name}: no Paparazzi snapshot and no docs copy.\n"
                f"Run: ./gradlew :app:recordPaparazziDebug\n"
                f"  or restore docs/images/mockups/{dst_name}"
            )
        else:
            print(f"using existing {dst_name}")

        im = Image.open(dst).convert("RGB")
        if im.size != (CANVAS_W, CANVAS_H):
            raise ValueError(f"{dst_name}: expected {CANVAS_W}x{CANVAS_H}, got {im.size}")
        exported.append(im)
        print(f"  {dst_name} {im.size}")

    return exported


def compose_overview(images: list[Image.Image], display_h: int = 960) -> Image.Image:
    """Lay out 3 phone mockups horizontally on white background."""
    scaled = [im.resize((int(im.width * display_h / im.height), display_h), RESAMPLE) for im in images]
    gap, pad_x, pad_y = 24, 32, 32
    total_w = pad_x * 2 + sum(s.width for s in scaled) + gap * (len(scaled) - 1)
    total_h = pad_y * 2 + display_h
    canvas = Image.new("RGB", (total_w, total_h), (255, 255, 255))
    x = pad_x
    for s in scaled:
        canvas.paste(s, (x, pad_y))
        x += s.width + gap
    return canvas


def main() -> None:
    images = export_snapshots()
    overview = compose_overview(images)
    overview_path = OUT_DIR / "overview.png"
    overview.save(overview_path, optimize=True)
    print(f"saved overview.png {overview.size}")


if __name__ == "__main__":
    main()
