package com.rest.testgeopagos.model

import com.google.gson.annotations.SerializedName

data class PaymentMethod(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("payment_type_id")
    val paymentTypeId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)


/*
*
* "id":"debvisa",
      "name":"Visa Débito",
      "payment_type_id":"debit_card",
      "status":"active",
      "secure_thumbnail":"https://www.mercadopago.com/org-img/MP3/API/logos/debvisa.gif",
      "thumbnail":"http://img.mlstatic.com/org-img/MP3/API/logos/debvisa.gif",
      "deferred_capture":"unsupported",
      "settings":[
      * {
      * "card_number":{
               "validation":"standard",
               "length":16

},
            "bin":{
               "pattern":"^(490889|409230|410352|476940|454970|20884|400276|400448|400615|400930|402789|402914|404022|404625|405069|405511|405515|405516|405517|405755|405896|405897|406165|406190|406191|406192|406193|406194|406195|406196|406290|406291|406375|406652|406998|406999|408134|408515|410082|410083|410121|410122|410123|410853|411197|411199|411849|412944|413180|416679|416861|417309|417856|417857|421518|421528|421541|421738|423001|423018|423077|423090|423465|423613|423613|423623|424968|424969|426618|427156|427157|428062|428063|428064|429751|429752|431070|431071|434531|434532|434533|434534|434535|434536|434537|434538|434539|434540|434541|434542|434543|434549|434550|434586|434795|437996|437999|438050|438051|438844|439818|441046|442371|442548|443264|444047|444060|444267|444268|444493|446343|446344|446345|446346|446347|448712|450412|450799|450811|451377|451701|451751|451756|451757|451758|451761|451763|451764|451765|451766|451767|451768|451769|451770|451772|451773|452132|452133|453770|455890|457308|457596|457664|457665|459300|462815|463465|464855|468508|469283|469874|472041|472042|473227|473365|473710|473711|473712|473713|473714|473715|473716|473717|473718|473719|473720|473721|473722|473725|474531|476520|477051|477053|477169|478017|478527|478601|480459|480460|480724|480860|481397|481501|481502|481550|483002|483020|483188|485089|485947|486547|486587|486621|486665|487221|488241|489412|489634|492499|492528|492596|492597|492598|499859)",
               "installments_pattern":null,
               "exclusion_pattern":"^(491580)"

},
            "security_code":{
               "length":3,
               "card_location":"back",
               "mode":"mandatory"

}

}

],
      "additional_info_needed":[
         "cardholder_name",
         "cardholder_identification_type",
         "cardholder_identification_number"

],
      "min_allowed_amount":1,
      "max_allowed_amount":700000,
      "accreditation_time":2880,
      "financial_institutions":[


],
      "processing_modes":[
         "aggregator"

]

}
*
* */