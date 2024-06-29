package com.example.lostpeoplefinder



object ReportDataBuffer {
    private var reportData: ReportData = ReportData(
        person_name = null,
        check_lost = null,
        age = null,
        date_of_lost = null,
        phone_number = null,
        email = null,
        lng = null,
        lat = null,
        gender = null,
        image_url = null,
        city = null,
        notes = null,
    )

    fun getReportData(): ReportData {
        return reportData
    }

    fun clearReportData() {
        reportData = ReportData(
            person_name = null,
            check_lost = null,
            age = null,
            date_of_lost = null,
            phone_number = null,
            email = null,
            lng = null,
            lat = null,
            gender = null,
            image_url = null,
            city = null,
            notes = null,
        )
    }

    fun updatePersonName(name: String?) {
        reportData?.person_name = name
    }

    fun getPersonName(): String? {
        return reportData?.person_name
    }

    fun updateCheckLost(checkLost: String?) {
        reportData?.check_lost = checkLost
    }

    fun getCheckLost(): String? {
        return reportData?.check_lost
    }
//    fun updateReportName(reportName: String?) {
//        reportData?.report_name = reportName
//    }
//
//    fun getReportName(): String? {
//        return reportData?.report_name
//    }

        fun updateAge(age: String?) {
        reportData?.age = age
    }

    fun getAge(): String? {
        return reportData?.age
    }

    fun updateDateOfLost(date: String?) {
        reportData?.date_of_lost = date
    }

    fun getDateOfLost(): String? {
        return reportData?.date_of_lost
    }

    fun updatePhoneNumber(phone: String?) {
        reportData?.phone_number = phone
    }

    fun getPhoneNumber(): String? {
        return reportData?.phone_number
    }

    fun updateEmail(email: String?) {
        reportData?.email = email
    }

    fun getEmail(): String? {
        return reportData?.email
    }

    fun updateLng(lng: String?) {
        reportData?.lng = lng
    }

    fun getLng(): String? {
        return reportData?.lng
    }

    fun updateLat(lat: String?) {
        reportData?.lat = lat
    }

    fun getLat(): String? {
        return reportData?.lat
    }

    fun updateGender(gender: Int?) {
        reportData?.gender = gender
    }

    fun getGender(): Int? {
        return reportData?.gender
    }

    fun updateImageUrl(url: String?) {
        reportData?.image_url = url
    }

    fun getImageUrl(): String? {
        return reportData?.image_url
    }

    fun updateCity(city: String?) {
        reportData?.city = city
    }

    fun getCity(): String? {
        return reportData?.city
    }

    fun updateNotes(notes: String?) {
        reportData?.notes = notes
    }

    fun getNotes(): String? {
        return reportData?.notes
    }

    override fun toString(): String {
        return reportData.toString()
    }
}

