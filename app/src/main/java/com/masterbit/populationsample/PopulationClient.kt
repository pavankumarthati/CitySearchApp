package com.masterbit.populationsample

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface PopulationClient {
//    @GET("countries/population/cities")
//    fun getPopulationCities(): Observable<PopulationResponse>

    @POST("countries/population/cities/filter")
    fun getFilteredPopulationCities(@Body body: Map<String, String>): Observable<PopulationResponse>

    companion object {
        fun getPopulationRestClient(): PopulationClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://countriesnow.space/api/v0.1/")
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build().create(PopulationClient::class.java)
        }

        fun mockedPopulationData(): Observable<PopulationResponse> {
            val jsonData = """[
    {
      "city": "MARIEHAMN",
      "country": "Åland Islands",
      "populationCounts": [
        {
          "year": "2013",
          "value": "11370",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "11304.5",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2011",
          "value": "11226.5",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "11156.5",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2009",
          "value": "11064",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2008",
          "value": "10954",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2007",
          "value": "10863",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2000",
          "value": "10488",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Durrës",
      "country": "Albania",
      "populationCounts": [
        {
          "year": "2011",
          "value": "113249",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "TIRANA",
      "country": "Albania",
      "populationCounts": [
        {
          "year": "2011",
          "value": "418495",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2003",
          "value": "392863",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Annaba",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "352523",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Batna",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "246800",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Béchar",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "134523",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Bejaïa",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "144405",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Beskra",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "177060",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Bordj Bou Arreridj",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "129004",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Bordj el Kiffan",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "103690",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Ech Cheliff (El Asnam)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "174314",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "El Boulaïda (Blida)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "229788",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "ALGIERS (EL DJAZAIR)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "1569897",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "El Djelfa",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "158679",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "El Eulma",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "104758",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "El Wad",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "105151",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Ghardaïa",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "127959",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Ghilizane",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "104644",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Guelma",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "108682",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Jijel",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "106306",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Lemdiyya (Medea)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "128427",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Mestghanem (Mostaganem)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "125911",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "M'Sila",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "102151",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Qacentina (Constantine)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "465021",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Saïda",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "113533",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Sidi-bel-Abbès",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "183931",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Skikda",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "153531",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Souq Ahras",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "114512",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Stif (Sétif)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "214842",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Tbessa",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "154335",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Tihert",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "148850",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Tilimsen (Tlemcen)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "156258",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Touggourt",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "114183",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Wahran (Oran)",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "705335",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Wargla",
      "country": "Algeria",
      "populationCounts": [
        {
          "year": "1998",
          "value": "139381",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "PAGO PAGO",
      "country": "American Samoa",
      "populationCounts": [
        {
          "year": "2010",
          "value": "3656",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2000",
          "value": "4278",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "ANDORRA LA VELLA",
      "country": "Andorra",
      "populationCounts": [
        {
          "year": "2011",
          "value": "22205",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "23505",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2009",
          "value": "24779",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2008",
          "value": "24632",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2007",
          "value": "24574",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2006",
          "value": "24211",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2005",
          "value": "23587",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2003",
          "value": "21245",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "THE VALLEY",
      "country": "Anguilla",
      "populationCounts": [
        {
          "year": "2001",
          "value": "4904",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "ST. JOHN",
      "country": "Antigua and Barbuda",
      "populationCounts": [
        {
          "year": "1991",
          "value": "22342",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Bahía Blanca-Cerri",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "317325.704982449",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "314948",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "310151",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "260096",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "BUENOS AIRES",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "13339001.6015536",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "13242375",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "13047383.5911513",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "2965403",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "11298030",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Catamarca",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "215795.389398648",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "212174",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "204865.910814638",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "109882",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "132626",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Comodoro Rivadavia-Rada Tilly",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "146557.365847925",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "145475",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "143291.153938137",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "124104",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Concordia",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "157705.748550135",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "155905",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "152271.040291904",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "116485",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Córdoba",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "1442000.64659388",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "1429922",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "1405547.38043669",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "1157507",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "1208554",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "140896",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "316032",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "328773",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2011",
          "value": "328325",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2011",
          "value": "328750",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "328603",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2009",
          "value": "328488",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2008",
          "value": "325453",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2006",
          "value": "322867",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2001",
          "value": "308072",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2001",
          "value": "303874",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2001",
          "value": "412759",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Corrientes",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "373981.279268183",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "368778",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "358278.429569445",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "258103",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "Formosa",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "253508.104025216",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "249062",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "240088.945193687",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "147636",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "88171",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "92023",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "La Plata",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "764468.872784661",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "757980",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "744885.526810173",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "521936",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "1991",
          "value": "642979",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "La Rioja",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "193763.58807037",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "189845",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "181938.392736754",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    },
    {
      "city": "San Luis - El Chorrillo",
      "country": "Argentina",
      "populationCounts": [
        {
          "year": "2013",
          "value": "216609.517202332",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2012",
          "value": "212154",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        },
        {
          "year": "2010",
          "value": "203161.629153992",
          "sex": "Both Sexes",
          "reliabilty": "Final figure, complete"
        }
      ]
    }]""".trimIndent()
            val list = Gson().fromJson<List<CityItem>>(jsonData, object : TypeToken<List<CityItem>>() {}.type)
            val response = PopulationResponse(false, "", list)
            return Observable.just(response)
        }
    }
}