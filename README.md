# 📦🧩 YouTube VR Patches

Morphe patches for YouTube VR (Meta Quest / Horizon OS).

## ❓ About

Patches for the YouTube VR app for Meta Quest (`com.google.android.apps.youtube.vr.oculus`).

YouTube VR is a heavily obfuscated XR app, so many patches made for the regular
YouTube app do not apply here (Shorts, miniplayer, navigation bar, fullscreen
gestures, etc.). This repository contains the patches that make sense on a VR
headset, matched with fingerprints that survive app updates.

### How to use these patches

Click here to add these patches to Morphe: https://morphe.software/add-source?github=WalkTheEarth/morphe-ytvr-patches

Supported app versions:

| 1.61.48 |
| :---: |

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->

<!-- Do not modify this section by hand. The patch list is generated when release.yml creates a new release.

     If you wish for the patches list to be collapsed, then remove the word 'EXPANDED' from the comment tag above.

     If you wish to manually keep this list updated then remove the PATCHES_START and PATCHES_END
     comment blocks entirely. -->

#### A list of your patches will automatically be shown here after your first patches release is created.

&nbsp;

## 🚀 Getting development started

1. [Setup](https://github.com/MorpheApp/morphe-documentation/blob/main/docs/morphe-development/README.md) your development environment including adding a GitHub PAT as described [here](https://github.com/MorpheApp/morphe-patcher/blob/main/docs/2_1_setup.md#-prepare-the-environment).
2. Clone the repository

   ```bash
   git clone https://github.com/WalkTheEarth/morphe-ytvr-patches
   cd morphe-ytvr-patches
   ```

3. Build the patches

   ```bash
   ./gradlew buildAndroid
   ```

## 🧑‍💻 Dev usage

- **Make all changes to the `dev` branch.**
- Build the `.mpp` patch bundle locally with `./gradlew buildAndroid`. The file is found in `patches/build/libs/patches-*.mpp`. Apply it with Morphe Desktop like any other patch bundle.
- Always use [Semantic commit](https://kapeli.com/cheat_sheets/Semantic_Commits.docset/Contents/Resources/Documents/index) messages for commits (`feat:`, `fix:`, `chore:`).
- Commits of `fix:` and `feat:` automatically generate new pre-releases; `chore:` does not.
- To make a stable release, merge `dev` to `main` (do not squash, only merge).
- **Always use semantic release (release.yml).** Do not manually create releases, because many files must be updated and release.yml handles everything.
- Do not manually edit or commit the generated files `patches-list.json`, `patches-bundle.json`, `CHANGELOG.md`. They are updated automatically by release.yml.

## 🤓 Tips

- See the [patcher documentation](https://github.com/MorpheApp/morphe-patcher/blob/main/docs/1_patcher_intro.md) for more examples of creating patches and fingerprints.
- Do not use AI to create new release scripts. The `release.yml` here already handles everything.

<!-- The patches end tag is intentionally placed here so the first release will clean up
     this readme of all developer instructions above. -->
<!-- PATCHES_END -->

### 🛠️ Building locally

- Run `./gradlew buildAndroid`
- The built patches .mpp file is found in `patches/build/libs/patches-*.mpp`
- Patch the mpp file using [Morphe-Desktop](https://github.com/MorpheApp/morphe-desktop) like any other patch bundle.

See the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation) for more information.

## 📜 License

YouTube VR Patches are licensed under the [GNU General Public License v3.0](LICENSE)
