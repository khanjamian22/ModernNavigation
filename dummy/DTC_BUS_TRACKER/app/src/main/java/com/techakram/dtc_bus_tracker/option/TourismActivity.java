package com.techakram.dtc_bus_tracker.option;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.techakram.dtc_bus_tracker.R;
import com.techakram.dtc_bus_tracker.adapter.Adapter;
import com.techakram.dtc_bus_tracker.model.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class TourismActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter myAadpter ;
    List<ModelClass> modelClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //List<ModelClass> modelClass=new ArrayList<>();
        //or
        modelClass = new ArrayList<>( );
        modelClass.add(new ModelClass("Red Fort",R.drawable.red_fort,"The Red sandstone walls of massive Red Fort (Lal Qila) rise 33-m above the clamour of Old Delhi as a reminder of the magnificent power and emperors. The pomp of the Mughal walls, built in 1638. \" +\n" +
                "\t\t        \t\t\"The main gate, Lahore Gate, is one of the emotional and symbolic focal points of the modern Indian nation and on crowd major attracts Independence Day"));
        modelClass.add(new ModelClass("Jama Masjid",R.drawable.jama_masjid,"Jama Masjid,is located in Chandni Chowk, New Delhi, Phone.:-011-23365358 and it was commissioned to be constructed by the Mughal Emperor Shah Jahan. \" +\n" +
                "\t\t        \t\t\"It holds the distinction of being one of the biggest and the most well known mosque of Old Delhi. Due to its setting at a very prominent center in Old Delhi, a lot of visitors visit it right through the year. "));
        modelClass.add(new ModelClass("AKSHARDHAM TEMPLE",R.drawable.akshardham_temple,"The Akshardham constructed of recent times and was inaugurated temple Bochasanvasi Swaminarayan Sanstha \" +\n" +
                "\t\t         \t\t\"on the banks of the River Yamuna, Open timings 9 am to 8 pm(Monday closed).The temple stretches over an area of 100 acres and was completed in two years."));
        modelClass.add(new ModelClass("ANSAL PLAZA",R.drawable.ansal_plaza,"Ansal Plaza offers the customers a world class shopping experience located near South Extensio in South Delhi. \" +\n" +
                "\t\t          \t\t\"This huge plaza is built on 35 acres of land.An auditorium with a stage is located at the centre while the 45-feet high splendid Ansal Plaza is built around it.\" +\n" +
                "\t\t\t\t\t\t\" The Plaza has a French glass curtain wall that to keep away ultraviolet and other harmful radiation."));
        modelClass.add(new ModelClass("APPU GHAR",R.drawable.appu_ghar,"Appu Ghar was an amusement park located in the Pragati Maidan in Delhi. This was the first amusement park of India, \" +\n" +
                "\t\t\t\t\t\t\"and was inaugurated by Late Shri Rajiv Gandhi, who was the Prime Minister of India. Appu Ghar closed on 17th Feb 2008. Appu Ghar was opened on November 19, 1984 \" +\n" +
                "\t\t\t\t\t\t\"and was named from the name 'Appu' which was the mascot of the 1982 Asian Games. It stretched over 15.5 acres of land. "));
        modelClass.add(new ModelClass("ART HERITAGE",R.drawable.art_heritage,"A respected name in the art world was founded in 1977 by Roshen Alkazi. The gallery holds regular exhibitions throughout \" +\n" +
                "\t\t\t\t\t\t\"the year of some of the best known artists in India.205, Triveni Kala Sangam, Tansen Marg, New Delhi.Phone.:23718833. Timing: 11.00 am to 7.00 pm ."));
        modelClass.add(new ModelClass("CHANDNI CHOWK",R.drawable.chandni_chowk,"Chandni Chowk is one of the oldest market places that,it has still retained its charm.Chandni Chowk is located opposite the Red Fort. \" +\n" +
                "\t\t        \t\t\"At one end of the Chandni Chowk, one which Mosque, been built by the wives of Shah Jahan. At the other end of Chandni Chowk, is the old police station or the Kotwali.It is famous for shopping all types of goods"));
        modelClass.add(new ModelClass("DILLI HAAT INA",R.drawable.dilli_haat_ina,"Dilli Haat stands for the variety Indian foods and customs in a single roomy enclosed space of six acres. A permanent and improvised \" +\n" +
                "\t\t\t\t\t\t\"adaptation of a traditional village haat, it is actually a mixture of a food plaza and craft marketplace, located in the locality of South Delhi"));
        modelClass.add(new ModelClass("GARHI LALIT KALA ARTIST STUDIO",R.drawable.garhi_lalit_kala_artist_studio," It is a Place worth visiting place if you are interested in the live processes of art. This studio is a part of and is run, The Lalit Kala Academy. \" +\n" +
                "\t\t        \t\t\"Well known artists from India and abroad often hold workshops and illustrated lectures here.Kala Kutir, East of Kailash, New Delhi."));
        modelClass.add(new ModelClass("GREATER KAILASH MARKET",R.drawable.greater_kailash_market,"The Greater Kailash area of South Delhi has it all; lavish cozy homes, elegant dresses, brisk crowd and bountiful markets. \" +\n" +
                "\t\t        \t\t\"One of the most colorful and posh markets of Delhi, this place is a real paradise. Magnificent show rooms and retail outlets selling designer wears, restaurants & easy accessibility give the \" +\n" +
                "\t\t        \t\t\"Greater Kailash market the edge."));
        modelClass.add(new ModelClass("GURUDWARA BANGLA SAHIB",R.drawable.gurudwara_bangla_sahib,"Gurudwara Bangla Sahib is located next to Gol Dak Khana near the Connaught Place. This place of Sikh worship is open to people of all faiths, castes or creeds. \" +\n" +
                "\t\t        \t\t\"The premises house a sacred pond in which devotees bathe. They believe that this would wash off their misdeeds and thus allow them to attain peace of mind."));
        modelClass.add(new ModelClass("HUMAYUN TOMB",R.drawable.humayun_tomb,"It is located near the crossing of Mathura road and Lodhi road, this magnificent garden tomb is the first substantial example of Mughal architecture in India.\" +\n" +
                "\t\t        \t\t\"It was built in 1565 A.D. nine years after the death of Humayun, by his senior widow Bega Begam. Inside the walled enclosure the most notable features are the garden squares (chaharbagh) with pathways water channels."));
        modelClass.add(new ModelClass("INDIA GATE",R.drawable.india_gate,"At the centre of New Delhi stands the 42 m Triomphe like archway in the middle of a crossroad.It commemorates the 70,000 peoples fighting for the British Army during the World War I. \" +\n" +
                "\t\t        \t\t\"The memorial bears the names of more than 13,516 British and Indian soldiers killed in the Northwestern war of 1919. The monument was dedicated to the nation and known by Amar Jawan Jyoti"));
        modelClass.add(new ModelClass("INDIRA GANDHI MEMORIAL",R.drawable.indira_gandhi_memorial,"Indira Gandhi Memorial Museum was the residence of the former Prime Minister of India. It was later converted into a museum. \" +\n" +
                "\t\t        \t\t\"One can see the collection of rare photographs of the Nationalist movement, the personal moments of the Nehru-Gandhi family and her childhood. Location : No. 1, Safderjang Road, New Delhi- 110 011. Timing: 9.30am To 5:00 pm Closed: Monday."));
        modelClass.add(new ModelClass("ISKCON TEMPLE",R.drawable.iskcon_temple,"The ISKCON Temple is located at Hare Krishna Hill, Sant Nagar, East of Kailash,New Delhi,DL-110065. Phone.:- 011-26235133 and was completed in 1998 as a complex of temples. \" +\n" +
                "\t\t        \t\t\"This temple has been built on a hilly terrain and is dedicated to the Lord Krishna."));
        modelClass.add(new ModelClass("JANTAR MANTAR",R.drawable.jantar_mantar,"Jantar Mantar is located at Sansad Marg, Janpath, Connaught Place,New Delhi,DL-110001 Phone:- 011-23365358 Open: All days ,Timings: 6:00 am – 7:00 pm Entry Fee: Rs.5(Indians),Rs.100(foreigners).\" +\n" +
                "\t\t        \t\t\"Jantar Mantar(jantra- instruments, mantra- formulae) was constrcted in 1724."));
        modelClass.add(new ModelClass("LODHI GARDEN",R.drawable.lodhi_garden,"The Lodhi Gardens is a recreational area in Delhi,located at Amrita Shergill Ln, Lodhi Gardens, Lodi Estate, New Delhi,DL-110003 Timimg: 6.00 AM - 7.00 PM and situated between Khan Market and Safdarjung's Lodhi Road. \" +\n" +
                "\t\t        \t\t\"In the middle of Lodhi and Sayyid aristocratic beautiful gardens is the Bara Gumbad or the 'Big Dome' and Sheesh'mirror dome'"));
        modelClass.add(new ModelClass("LOTUS TEMPLE",R.drawable.lotus_temple,"Lotus Temple is situated at Lotus Temple Rd, Shambhu Dayal Bagh, Bahapur, New Delhi, Delhi 110019 Pnone.:- 011-23389326 Timing: 9:30 am – 5:30 pm.It is incredible architectures of the faith.The temple has been constructed to resemble a lotus flower. \" +\n" +
                "\t\t        \t\t\"The huge lotus flower has been made out of marble, dolomite, cement, and sand.The place is known for its spotlessly clean environment."));
        modelClass.add(new ModelClass("NATIONAL ZOOLOGICAL PARK",R.drawable.national_zoological_park,"It is located near the Old Fort,Mathura Rd, New Delhi,DL-110003 TIMINGS: 9:00am - 4:30pm  Monday closed, Entrance Fee:Indians: Rs.10(Adults), Rs.5(Children from 5-12 years) and Foreigners: Rs.50(Adults).\" +\n" +
                "\t\t        \t\t\"It is stretches across 214 acres of land.The park is home to several species of animals as well as various types of vegetation. It offers a natural environment to over 2,000 birds and animals"));
        modelClass.add(new ModelClass("NEHRU PARK",R.drawable.nehru_park," Nehru Park, Delhi, is large park situated at Vinay Marg, Chanakyapuri Diplomatic Enclave of New Delhi, DL-110021. Named after India's first Prime Minister, \" +\n" +
                "\t\t        \t\t\"Jawaharlal Nehru, the park is spread over an area of 80 acres,close to the heart of the city, and was established in 1969."));
        modelClass.add(new ModelClass("PARLIAMENT HOUSE",R.drawable.parliament_house,"The Parliament House is located at Lok Sabha Marg, Gokul Nagar,Pandit Pant Marg Area, Central Secretariat, New Delhi, DL-110001. \" +\n" +
                "\t\t        \t\t\"The Parliament House (Sansad Bhavan) is a circular building designed by the British architects Sir Edwin Lutyens and Sir Herbert Baker in 1912–1913"));
        modelClass.add(new ModelClass("PRAGATI MAIDAN",R.drawable.pragati_maidan,"It is loacted at Mathura Rd, Pragati Maidan New Delhi,DL-110002.It\u200E is a venue for large exhibitions and conventions in New Delhi, \" +\n" +
                "\t\t        \t\t\"and with 72,000 sq. metres of exhibition space,it is Delhi's largest exhibition centre. It is owned and managed by India Trade Promotion Organization (ITPO), Govt. of India."));
        modelClass.add(new ModelClass("PURANA QILA",R.drawable.purana_qila,"Purana Qila or the Old Fort is situated at Pragati Maidan,Mathura Rd,New Delhi,Delhi-110003 Phone:-011-23365358. Purana Quila standing stoically amidst wild greenery.\" +\n" +
                "\t\t        \t\t\"Built on the site of the most ancient of the numerous cities of Delhi, Indraprastha, Purana Quila is roughly rectangular in shape having a circuit of nearly two kilometers."));
        modelClass.add(new ModelClass("QUTAB MINAR",R.drawable.qutab_minar,"Qutab Minar is located near Mehrauli,New Delhi Open Timimgs: 6:00 am – 6:00 pm.It is a soaring, 73 m-high tower of victory & is made of red sandstone and marble, \" +\n" +
                "\t\t        \t\t\"built in 1193 by Qutab-ud-din Aibak immediately after the defeat of Delhi's last Hindu kingdom. The tower has five distinct storeys, each marked by a projecting balcony and tapers from a 15 m diameter at the base to just 2.5 m at the top."));
        modelClass.add(new ModelClass("RAJ GHAT",R.drawable.raj_ghat,"Raj Ghat is located on the banks of Yamuna River Open timimg: 5:30 am to 7 pm and was built as a cenotaph for honouring Mahatma Gandhi. There is a grave black marble podium at this site which " +
                "is the spot of cremation of Mahatma Gandhi done on 31st January 1948"));
        modelClass.add(new ModelClass("RAJPATH",R.drawable.rajpath,"RajPath is said commissioned India Gate and its surrounding area on Rajpath, RajRoad Open Timing: 12:00 AM - 12:00 PM. It is the traditional avenue of there public Indian Republic day parade passes every year. \" +\n" +
                "\t\t        \t\t\"It boasts of decorative parks, pools and gardens along extends from the India Gate to Vijay Chowk, and Bhavan and National Stadium at two opposite ends."));
        modelClass.add(new ModelClass("RASHTRAPATI BHAVAN",R.drawable.rashtrapati_bhavan,"Rashtrapati Bhavan(Presidential Residence) is the official home of the President of India. It is situated in the Raisina Hills,Delhi Phone:- 011-23013287, open Timings 9:00am to 4:00pm(Fri-Sun). \" +\n" +
                "\t\t        \t\t\"It may refer to only the mansion (the 340-room main building) that has the President's official residence, halls, guest rooms and offices. The main palace building was formerly known as Viceroy's House."));
//        modelClass.add(new ModelClass("",R.drawable.rashtrapati_bhavan,""));
//
        myAadpter=new Adapter(this,modelClass);
        recyclerView.setAdapter(myAadpter);
    }
}