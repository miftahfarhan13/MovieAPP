package com.example.moviecatalogueuiux.Fragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogueuiux.R;
import com.example.moviecatalogueuiux.Model.MovieModel;
import com.example.moviecatalogueuiux.Adapter.MovieAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ArrayList<MovieModel> movieModels = new ArrayList<>();
    private MovieAdapter movieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie,container,false);
        addData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieModels);
        recyclerView.setAdapter(movieAdapter);
        return view;
    }


    void addData() {
        movieModels = new ArrayList<>();
        movieModels.add(new MovieModel(R.drawable.poster_a_start_is_born, "A Star Is Born", "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally\\'s career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.", "75", "October 3, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_alita, "Alita: Battle Angel", "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.", "67", "January 31, 2019"));
        movieModels.add(new MovieModel(R.drawable.poster_aquaman, "Aquaman", "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm\\'s half-human, half-Atlantean brother and true heir to the throne.", "68", "December 7, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_bohemian, "Bohemian Rhapsody", "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock \\'\\n\\' roll band Queen in 1970. Hit songs become instant classics. When Mercury\\'s increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.", "81", "October 24, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_cold_persuit, "Cold Persuit", "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son\\'s murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking\\'s associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.", "54", "February 7, 2019"));
        movieModels.add(new MovieModel(R.drawable.poster_creed, "Creed II", "Between personal obligations and training for his next big fight against an opponent with ties to his family\\'s past, Adonis Creed is up against the challenge of his life.", "67", "November 21, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_crimes, "Fantastic Beasts: The Crimes of Grindelwald", "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.", "69", "November 14, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_glass, "Glass", "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.", "65", "January 16, 2019"));
        movieModels.add(new MovieModel(R.drawable.poster_how_to_train, "How to Train Your Dragon: The Hidden World", "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless\\’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup\\’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.", "77", "January 3, 2019"));
        movieModels.add(new MovieModel(R.drawable.poster_infinity_war, "Avengers: Infinity War", "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy…", "83", "April 25, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_marry_queen, "Mary Queen of Scots", "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.", "66", "December 7, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_master_z, "Master Z: Ip Man Legacy", "After being defeated by Ip Man, Cheung Tin Chi is attempting to keep a low profile. While going about his business, he gets into a fight with a foreigner by the name of Davidson, who is a big boss behind the bar district. Tin Chi fights hard with Wing Chun and earns respect.", "52", "December 20, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_mortal_engines, "Mortal Engines", "Many thousands of years in the future, Earth\\’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.", "60", "November 27, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_overlord, "Overlord", "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.", "66", "November 1, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_ralph, "Ralph Breaks the Internet", "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope\\'s video game, \\\"\\Sugar Rush.\\\"\\ In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.", "72", "November 20, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_robin_hood, "Robin Hood", "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.", "58", "November 20, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_serenity, "Serenity", "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.", "51", "January 24, 2019"));
        movieModels.add(new MovieModel(R.drawable.poster_spiderman, "Spider-Man: Into the Spider-Verse", "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \\\"\\Kingpin\\\"\\ Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.", "84", "December 6, 2018"));
        movieModels.add(new MovieModel(R.drawable.poster_t34, "T-34", "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.", "49", "JDecember 27, 2018"));
    }


}
