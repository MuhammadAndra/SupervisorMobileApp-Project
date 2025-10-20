package com.example.supervisormobileapp_project.data.model

fun fetchCompanies(): List<Company> {
    return companyList
}

fun fetchPatrolSpots(companyId: Int): List<PatrolSpot> {
    return patrolSpotList.filter { it.companyId == companyId }
}

fun fetchPatrolSpotById(id: Int): PatrolSpot {
    return patrolSpotList.first { it.id == id }
}

//fun fetchPatrolSpotByNfcUid(nfcUid: String): PatrolSpot {
//    return patrolSpotList.first { it.uidNfcTag == nfcUid }
//}

fun fetchPatrolSpotByNfcUidResponse(nfcUid: String): ReadNFCResponse {
    val data: PatrolSpot? = patrolSpotList.firstOrNull { it.uidNfcTag == nfcUid }
    return ReadNFCResponse(
        status = if (data!=null) "success" else "error",
        message =  "NFC Tag UID${ if (data!=null) " " else " not "}compatible with database" ,
        data = data

    )
}


fun editPatrolSpot(id: Int, newSpot: PatrolSpot) {
    val index = patrolSpotList.indexOfFirst { it.id == id }
    if (index != -1) {
        patrolSpotList[index] = newSpot
    }
}

fun fetchSupervisorData(): Supervisor {
    return dummySupervisor
}

fun changeSupervisorData(supervisor: Supervisor) {
    dummySupervisor = supervisor
}

//list dummy
val companyList = mutableListOf(
    Company(
        id = 1,
        name = "Fakultas Ilmu Komputer Universitas Brawijaya",
        address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Tim..",
        logo = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtoRD2IW8OpUHuzrPbXKj9E28d-DWZhPBJLRUP2H9rKpLbKAsnDpD9ViWdTXwBdCThRzo&usqp=CAU"
    ),
    Company(
        id = 2,
        name = "Malang Creative Center",
        address = "Jl. Ahmad Yani No.53, Blimbing, Kec. Blimbing, Kota Malang, Jawa Timur 65118",
        logo = "https://mcc.or.id/wp-content/uploads/2022/05/MCC-Logo.png"
    ),
    Company(
        id = 3,
        name = "Mall Olympic Garden",
        address = "Kawi St No.24, Kauman, Klojen, Malang City, East Java 65116",
        logo = "https://pbs.twimg.com/profile_images/1465719530/MOG1_400x400.jpg"
    ),
    Company(
        id = 4,
        name = "Pemerintah Kota Malang",
        address = "Jl. Tugu No.1, Kiduldalem, Kec. Klojen, Kota Malang, Jawa Timur 65119",
        logo = "https://malangkota.go.id/wp-content/uploads/2023/06/logo-malang.png"
    ),
    Company(
        id = 5,
        name = "Malang Town Square",
        address = "Jl. Veteran No.2, Penanggungan, Kec. Klojen, Kota Malang, Jawa Timur 65111",
        logo = "https://tsp.malangkota.go.id/storage/files/site/6gFcS3GyyRhPFjmYqmswLKB014Kh5CeDHYvr04bV.png"
    ),
    Company(
        id = 6,
        name = "RSI Unisma",
        address = "Jalan Mayjen Haryono No.139, Dinoyo, Kec. Lowokwaru, Kota Malang",
        logo = "https://tsp.malangkota.go.id/storage/files/site/h8Stfw0iOZxWfIhS0KJoTz0Qm7IQb7Hco887Ismx.png"
    )
)

val patrolSpotList = mutableListOf(
    // Company 1: FILKOM UB
    PatrolSpot(
        companyId = 1,
        id = 1,
        name = "Gedung F Lantai 1",
        address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145",
        latitude = "-7.953810836342468",
        longitude = "112.61454711534338",
        description = "Area Lobby dan Kemahasiswaan",
        uidNfcTag = "0484095538",
    ),
    PatrolSpot(
        companyId = 1,
        id = 2,
        name = "Gedung F Lantai 2",
        address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145",
        latitude = "-7.953810836342468",
        longitude = "112.61454711534338",
        description = "Area pembelajaran dan ruang baca",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 1,
        id = 3,
        name = "Gedung F Lantai 3",
        address = "Ruang A1 No.19, Ketawanggede, Kec. Lowokwaru, Kota Malang, Jawa Timur 65145",
        latitude = "-7.953810836342468",
        longitude = "112.61454711534338",
        description = "Area pembelajaran",
        uidNfcTag = null,
    ),

    // Company 2: Malang Creative Center
    PatrolSpot(
        companyId = 2,
        id = 4,
        name = "Studio Multimedia",
        address = "Jl. Ahmad Yani No.53, Blimbing, Kec. Blimbing, Kota Malang, Jawa Timur 65118",
        latitude = "-7.942182",
        longitude = "112.637821",
        description = "Area produksi video dan konten kreatif",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 2,
        id = 5,
        name = "Ruang Inkubasi Startup",
        address = "Jl. Ahmad Yani No.53, Blimbing, Kec. Blimbing, Kota Malang, Jawa Timur 65118",
        latitude = "-7.941893",
        longitude = "112.637111",
        description = "Area kerja startup binaan MCC",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 2,
        id = 6,
        name = "Auditorium MCC",
        address = "Jl. Ahmad Yani No.53, Blimbing, Kec. Blimbing, Kota Malang, Jawa Timur 65118",
        latitude = "-7.942495",
        longitude = "112.636920",
        description = "Area kegiatan seminar dan workshop",
        uidNfcTag = null,
    ),

    // Company 3: Mall Olympic Garden
    PatrolSpot(
        companyId = 3,
        id = 7,
        name = "Area Food Court",
        address = "Kawi St No.24, Kauman, Klojen, Malang City, East Java 65116",
        latitude = "-7.977512",
        longitude = "112.630018",
        description = "Area makan pengunjung lantai atas",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 3,
        id = 8,
        name = "Parkiran Basement",
        address = "Kawi St No.24, Kauman, Klojen, Malang City, East Java 65116",
        latitude = "-7.977800",
        longitude = "112.630210",
        description = "Area parkir kendaraan pengunjung",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 3,
        id = 9,
        name = "Lobby Utama",
        address = "Kawi St No.24, Kauman, Klojen, Malang City, East Java 65116",
        latitude = "-7.977600",
        longitude = "112.629980",
        description = "Area masuk utama mall dan pusat informasi",
        uidNfcTag = null,
    ),

    // Company 4: Pemerintah Kota Malang
    PatrolSpot(
        companyId = 4,
        id = 10,
        name = "Ruang Rapat Walikota",
        address = "Jl. Tugu No.1, Kiduldalem, Kec. Klojen, Kota Malang, Jawa Timur 65119",
        latitude = "-7.981345",
        longitude = "112.631122",
        description = "Ruang rapat utama untuk pertemuan resmi",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 4,
        id = 11,
        name = "Lobby Balai Kota",
        address = "Jl. Tugu No.1, Kiduldalem, Kec. Klojen, Kota Malang, Jawa Timur 65119",
        latitude = "-7.981589",
        longitude = "112.631412",
        description = "Area penerimaan tamu dan pelayanan publik",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 4,
        id = 12,
        name = "Ruang Arsip dan Dokumentasi",
        address = "Jl. Tugu No.1, Kiduldalem, Kec. Klojen, Kota Malang, Jawa Timur 65119",
        latitude = "-7.981210",
        longitude = "112.631780",
        description = "Area penyimpanan arsip dan dokumen penting",
        uidNfcTag = null,
    ),

    // Company 5: Malang Town Square
    PatrolSpot(
        companyId = 5,
        id = 13,
        name = "Area Bioskop",
        address = "Jl. Veteran No.2, Penanggungan, Kec. Klojen, Kota Malang, Jawa Timur 65111",
        latitude = "-7.957442",
        longitude = "112.619543",
        description = "Patroli di area cinema dan koridor sekitar",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 5,
        id = 14,
        name = "Area Parkiran Barat",
        address = "Jl. Veteran No.2, Penanggungan, Kec. Klojen, Kota Malang, Jawa Timur 65111",
        latitude = "-7.957211",
        longitude = "112.619700",
        description = "Area parkir kendaraan dan pos keamanan",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 5,
        id = 15,
        name = "Food Court Matos",
        address = "Jl. Veteran No.2, Penanggungan, Kec. Klojen, Kota Malang, Jawa Timur 65111",
        latitude = "-7.957650",
        longitude = "112.619380",
        description = "Area kuliner pengunjung lantai 3",
        uidNfcTag = null,
    ),

    // Company 6: RSI Unisma
    PatrolSpot(
        companyId = 6,
        id = 16,
        name = "Ruang ICU",
        address = "Jalan Mayjen Haryono No.139, Dinoyo, Kec. Lowokwaru, Kota Malang",
        latitude = "-7.946543",
        longitude = "112.615890",
        description = "Area perawatan intensif pasien",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 6,
        id = 17,
        name = "Ruang Operasi",
        address = "Jalan Mayjen Haryono No.139, Dinoyo, Kec. Lowokwaru, Kota Malang",
        latitude = "-7.946812",
        longitude = "112.616001",
        description = "Area operasi dan sterilisasi medis",
        uidNfcTag = null,
    ),
    PatrolSpot(
        companyId = 6,
        id = 18,
        name = "Ruang Farmasi",
        address = "Jalan Mayjen Haryono No.139, Dinoyo, Kec. Lowokwaru, Kota Malang",
        latitude = "-7.946730",
        longitude = "112.615760",
        description = "Tempat penyimpanan dan distribusi obat",
        uidNfcTag = null,
    )
)

var dummySupervisor = Supervisor(
    fullName = "Budi Setiawan",
    nickname = "Budi",
    nip = "1954628756123679564",
    jobStatus = "Contract",
    position = "Supervisor",
    department = "Supervisor",
    gender = "Male",
    religion = "Islam",
    address = "Jl. Simpang Bermuda No.1 Malang"
)