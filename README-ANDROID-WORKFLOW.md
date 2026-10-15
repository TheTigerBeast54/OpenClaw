# Captain Claw Android bootstrap — GitHub Actions

This package adds an initial Android/SDL2 Gradle shell and a manually runnable GitHub Actions workflow to an OpenClaw fork.

## Install the files

1. Fork https://github.com/pjasicek/OpenClaw on GitHub.
2. Download this bootstrap ZIP and extract it.
3. Copy `.github/` and `android-app/` into the root of your fork, preserving the folder structure.
4. Commit and push the files to your fork's `main` branch (or `android-port` branch).
5. Open your fork on GitHub → **Actions** → **Build Captain Claw Android APK** → **Run workflow**.
6. If it succeeds, open the completed run and download the `captainclaw-android-debug` artifact.

## Important status

This is a build bootstrap, not a verified working port. The upstream repository's Android CMake file is obsolete, and the upstream root CMake configuration has legacy assumptions about SDL library targets and output paths. The first workflow run may fail at CMake configuration or compilation. If it does, download the failed run log and share the first actual error; patch the exact compatibility issue rather than guessing.

This initial APK shell does **not yet** implement:
- the original-game file picker / copy into app storage;
- runtime path handling for `CLAW.REZ` and `ASSETS.ZIP`;
- the transparent touch controls;
- audio validation on a physical Android device.

The workflow intentionally does not package copyrighted game data. Users must provide their own original files.

## License notes

OpenClaw is GPL-3.0. Keep upstream license notices and satisfy applicable source-distribution obligations for any distributed derivative. SDL and its extensions have their own licenses; retain their notices too.
