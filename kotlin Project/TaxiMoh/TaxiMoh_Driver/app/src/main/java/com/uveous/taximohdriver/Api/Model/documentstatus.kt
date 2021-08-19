package com.uveous.taximohdriver.Api.Model

import com.google.gson.annotations.SerializedName

data class documentstatus(
        @SerializedName("status")
        var status:Int,

        @SerializedName("driver_doc_status")
        val driver_doc_status :Driver_doc_status


)
class Driver_doc_status {

    private var background_check: background_check? = null

    private var profile_photo: profile_photo? = null

    private var driving_licence: driving_licence? = null

    private var vehicle_insurance: vehicle_insurance? = null

    private var vehicle_registration: vehicle_registration? = null

    fun setBackground_check(background_check: background_check) {
        this.background_check = background_check
    }

    fun getBackground_check(): background_check? {
        return this.background_check
    }

    fun setProfile_photo(profile_photo: profile_photo) {
        this.profile_photo = profile_photo
    }

    fun getProfile_photo(): profile_photo? {
        return this.profile_photo
    }

    fun setDriving_licence(driving_licence: driving_licence) {
        this.driving_licence = driving_licence
    }

    fun getDriving_licence(): driving_licence? {
        return this.driving_licence
    }

    fun setVehicle_insurance(vehicle_insurance: vehicle_insurance) {
        this.vehicle_insurance = vehicle_insurance
    }

    fun getVehicle_insurance(): vehicle_insurance? {
        return this.vehicle_insurance
    }

    fun setVehicle_registration(vehicle_registration: vehicle_registration) {
        this.vehicle_registration = vehicle_registration
    }

    fun getVehicle_registration(): vehicle_registration? {
        return this.vehicle_registration
    }
}

class vehicle_registration {
    @SerializedName("status")
    var status:String ?=""

}

class vehicle_insurance {
    @SerializedName("status")
    var status:String ?=""

}

class driving_licence {
    @SerializedName("status")
    var status:String ?=""
    @SerializedName("type")
    var type:String ?=""
    @SerializedName("value")
    var value:String ?=""


}

class profile_photo {
    @SerializedName("status")
    var status:String ?=""
    @SerializedName("type")
    var type:String ?=""
    @SerializedName("value")
    var value:String ?=""


}

class background_check {

    @SerializedName("status")
    var status:String ?=""
    @SerializedName("type")
    var type:String ?=""
    @SerializedName("value")
    var value:String ?=""


}
