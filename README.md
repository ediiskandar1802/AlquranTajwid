# Alquran Tajwid

Aplikasi Android untuk membaca Alquran dengan fitur Tajweed, Audio, dan Terjemahan Bahasa Indonesia.

**Pengembang:** Edi Iskandar  
**Versi:** 1.0.0

## Fitur Aplikasi

✅ **Daftar Surah** - Tampilan lengkap semua surah dengan jumlah ayat  
✅ **Pencarian** - Cari surah berdasarkan nama atau nomor  
✅ **Detail Ayat** - Lihat ayat dengan terjemahan bahasa Indonesia  
✅ **Audio Quran** - Putar audio bacaan Alquran  
✅ **Bookmark** - Simpan ayat favorit untuk dibaca nanti  
✅ **Tajweed Info** - Informasi aturan tajweed untuk setiap ayat  
✅ **Database Lokal** - Data tersimpan di device untuk akses offline  
✅ **Pengaturan** - Kustomisasi ukuran font dan tampilan  

## Persyaratan

- **Android Studio** versi terbaru (Jellyfish atau lebih baru)
- **Java Development Kit (JDK)** versi 11 atau lebih tinggi
- **Android SDK** dengan API Level 21+ (Android 5.0)

## Setup Proyek

### 1. Clone Repository
```bash
git clone https://github.com/ediiskandar1802/AlquranTajwid.git
cd AlquranTajwid
```

### 2. Buka di Android Studio
```bash
# Buka Android Studio
File → Open → Pilih folder AlquranTajwid
```

### 3. Sinkronisasi Gradle
Gradle akan otomatis mendownload semua dependencies yang diperlukan.

### 4. Tambahkan Data Quran
Buat file `quran_data.json` di `app/src/main/assets/` dengan struktur:

```json
[
  {
    "number": 1,
    "name": "Al-Fatihah",
    "arabicName": "الفاتحة",
    "revelation": "Makkah",
    "ayahCount": 7,
    "description": "Pembukaan",
    "ayahs": [
      {
        "number": 1,
        "text": "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
        "translation": "Dengan menyebut nama Allah Yang Maha Pengasih lagi Maha Penyayang.",
        "tajweedRules": "Tajweed info",
        "audioUrl": "https://url-to-audio.mp3"
      }
    ]
  }
]
```

**Sumber Data Gratis:**
- API: https://api.quran.com
- GitHub: https://github.com/ryanbekhen/quran-api

## Build APK

### Debug APK
```bash
# Via Android Studio: Build → Build Bundle(s) / APK(s) → Build APK(s)
# Atau via Command Line:
./gradlew assembleDebug
# File akan tersimpan di: app/build/outputs/apk/debug/
```

### Release APK (Signed)
```bash
# 1. Generate key untuk signing
keytool -genkey -v -keystore my-release-key.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias

# 2. Edit build.gradle di app/ untuk menambahkan signing config
# 3. Build release APK
./gradlew assembleRelease
# File akan tersimpan di: app/build/outputs/apk/release/
```

## Struktur Proyek

```
AlquranTajwid/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/ediiskandar/alqurantajwid/
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── SurahDetailActivity.java
│   │   │   │   ├── BookmarkActivity.java
│   │   │   │   └── SettingsActivity.java
│   │   │   ├── adapter/
│   │   │   │   ├── SurahAdapter.java
│   │   │   │   ├── AyahAdapter.java
│   │   │   │   └── BookmarkAdapter.java
│   │   │   ├── data/
│   │   │   │   ├── AppDatabase.java
│   │   │   │   └── BookmarkDao.java
│   │   │   ├── model/
│   │   │   │   ├── Surah.java
│   │   │   │   ├── Ayah.java
│   │   │   │   └── Bookmark.java
│   │   │   ├── service/
│   │   │   │   └── AudioService.java
│   │   │   └── util/
│   │   │       └── QuranDataManager.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── xml/
│   │   └── assets/
│   │       └── quran_data.json
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
└── README.md
```

## Dependencies

```gradle
// AndroidX
androidx.appcompat:appcompat:1.6.1
androidx.constraintlayout:constraintlayout:2.1.4
androidx.recyclerview:recyclerview:1.3.2
androidx.preference:preference:1.2.1

// Material Design
com.google.android.material:material:1.10.0

// Database
androidx.room:room-runtime:2.6.1
androidx.room:room-compiler:2.6.1

// JSON
com.google.code.gson:gson:2.10.1

// Networking
com.squareup.okhttp3:okhttp:4.11.0
```

## Cara Menggunakan Aplikasi

### 1. Membaca Surah
- Buka aplikasi
- Pilih surah dari daftar
- Tap pada surah untuk melihat detail
- Ayat ditampilkan dengan terjemahan bahasa Indonesia

### 2. Menambah Bookmark
- Buka detail surah
- Tap pada ayat yang ingin disimpan
- Pilih "Tambah Bookmark"
- Bookmark tersimpan di database lokal

### 3. Memutar Audio
- Di halaman detail surah, tap tombol play
- Audio bacaan akan dimulai
- Gunakan tombol pause/resume untuk kontrol

### 4. Pencarian
- Di halaman utama gunakan search bar
- Cari berdasarkan nama surah atau nomor
- Hasil filter ditampilkan secara real-time

## Troubleshooting

### Build Error: "Could not find com.android.tools.build:gradle:8.0.2"
**Solusi:** Update Android Studio ke versi terbaru dan sync gradle

### Error: "Cannot find symbol" pada model class
**Solusi:** Rebuild project: Build → Clean Project → Rebuild Project

### Audio tidak berfungsi
**Solusi:** Pastikan file `quran_data.json` memiliki field `audioUrl` yang valid

## Lisensi

Project ini dibuat untuk keperluan edukatif dan non-komersial.

## Kontak & Dukungan

- **GitHub:** [ediiskandar1802](https://github.com/ediiskandar1802)
- **Email:** edi.iskandar@example.com

---

**Dibuat dengan ❤️ oleh Edi Iskandar**
