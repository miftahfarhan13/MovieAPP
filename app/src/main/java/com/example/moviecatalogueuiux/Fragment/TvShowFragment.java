package com.example.moviecatalogueuiux.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogueuiux.Adapter.MovieAdapter;
import com.example.moviecatalogueuiux.Adapter.TvAdapter;
import com.example.moviecatalogueuiux.Model.MovieModel;
import com.example.moviecatalogueuiux.Model.TvShowModel;
import com.example.moviecatalogueuiux.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private ArrayList<TvShowModel> tvShowModels = new ArrayList<>();
    private TvAdapter tvAdapter;
    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show,container,false);
        addData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        TvAdapter tvAdapter = new TvAdapter(getContext(), tvShowModels);
        recyclerView.setAdapter(tvAdapter);
        return view;
    }

    void addData() {
        tvShowModels = new ArrayList<>();
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_arrow, "Arrow", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "58", "October 10, 2012"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_doom_patrol, "Doom Patrol", "The Doom Patrol/’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.", "61", "February 15, 2019"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_dragon_ball, "Dragon Ball", "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure that would change Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, Krillin, Master Roshi and more.", "71", "February 26, 1986"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_fairytail, "Fairy Tail", "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn/'t just any ordinary kid, he/'s a member of one of the world's most infamous mage guilds: Fairy Tail.", "64", "October 12, 2009"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_family_guy, "Family Guy", "Sick, twisted, politically incorrect and Freakin/' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.", "65", "January 31, 1999"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_flash, "The Flash", "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.", "67", "October 7, 2014"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_god, "Game of Thrones", "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.", "81", "April 17, 2011"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_gotham, "Gotham", "Everyone knows the name Commissioner Gordon. He is one of the crime world/'s greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker? ", "69", "September 22, 2014"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_grey_anatomy, "Grey/'s Anatomy", "Follows the personal and professional lives of a group of doctors at Seattle/’s Grey Sloan Memorial Hospital.", "62", "March 27, 2005"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_hanna, "Hanna", "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.", "64", "March 28, 2019"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_iron_fist, "Marvel/'s Iron Fist", "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.", "61", "March 17, 2017"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_naruto_shipudden, "Naruto Shippūden", "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.", "75", "February 15, 2007"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_ncis, "NCIS", "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.", "67", "September 23, 2003"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_riverdale, "Riverdale", "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale/’s wholesome facade.", "69", "January 26, 2017"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_shameless, "Shameless", "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he/'s passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.", "78", "OJanuary 9, 2011"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_supergirl, "Supergirl", "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.", "58", "October 26, 2015"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_supernatural, "Supernatural", "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ", "73", "September 13, 2005"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_the_simpson, "The Simpsons", "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.", "71", "December 17, 1989"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_the_umbrella, "The Umbrella Academy", "A dysfunctional family of superheroes comes together to solve the mystery of their father/'s death, the threat of the apocalypse and more.", "77", "February 15, 2019"));
        tvShowModels.add(new TvShowModel(R.drawable.tv_poster_the_walking_dead, "The Walking Dead", "Sheriff/'s deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.", "73", "October 31, 2010"));

    }


}
