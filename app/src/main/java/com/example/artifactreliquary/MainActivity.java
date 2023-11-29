package com.example.artifactreliquary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.artifactreliquary.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Button wikiButton, collectionButton, loginButton;

    public static UserDAO userDAO;
    List<User> userList;

    public static SetDAO setDAO;
    List<Set> setList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wikiButton = binding.wikiButton;
        wikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SetsActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        collectionButton = binding.collectionButton;
        collectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not ready yet", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton = binding.accountButton;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = userDAO.getActiveUser();
                if(userList.size()==0){
                    Intent intent = LoginOptionActivity.getIntent(getApplicationContext());
                    startActivity(intent);
                }else {
                    Intent intent = AccountOptionsActivity.getIntent(getApplicationContext());
                    startActivity(intent);
                }
            }
        });

        userDAO = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "Database")
                .allowMainThreadQueries()
                .build().getUserDAO();

        userList = userDAO.getUsers();
        if (userList.size() == 0) {
            userDAO.insert(
                    //populating default users
                    new User("admin", "secretAdminPassword", false, true),
                    new User("user", "userPassword", false, false)
                    );
            Toast.makeText(getApplicationContext(), "Populating Default Users", Toast.LENGTH_LONG).show();
        }

        setDAO = Room.databaseBuilder(this,
                        AppDatabase.class,
                        "Database")
                .allowMainThreadQueries()
                .build().getSetDAO();

        setList = setDAO.getSets();
        if (setList.size() == 0) {
            setDAO.insert(
                    //populating set table
                    new Set("Adventurer", "Max HP increased by 1,000", "Opening a chest regenerates 30% Max HP over 5s.", 1,3),
                    new Set("Lucky Dog", "DEF increased by 100", "Picking up Mora restores 300 HP.", 1,3),
                    new Set("Traveling Doctor", "Increases incoming healing by 20%", "Using an Elemental Burst restores 20% HP.", 1,3),
                    new Set("Resolution of Sojourner", "ATK +18%", "Increases Charged Attack CRIT Rate by 30%", 3,4),
                    new Set("Tiny Miracle", "All Elemental RES increased by 20%", "Incoming Elemental DMG increases corresponding Elemental RES by 30% for 10s. Can only occur once every 10s.",3,4),
                    new Set("Berserker", "CRIT Rate +12%", "When HP is below 70%, CRIT Rate increases by an additional 24%.",3,4),
                    new Set("Instructor", "Increases Elemental Mastery by 80", "Upon triggering an Elemental Reaction, increases all party members' Elemental Mastery by 120 for 8s.",3,4),
                    new Set("The Exile", "Energy Recharge +20%", "Using an Elemental Burst regenerates 2 Energy for all party members (excluding the wearer) every 2s for 6s. This effect cannot stack.",3,4),
                    new Set("Defender's Will", "DEF +30%", "For each different element present in your own party, the wearer's Elemental RES to that corresponding element is increased by 30%.", 3,4),
                    new Set("Brave Heart", "ATK +18%", "Increases DMG by 30% against opponents with more than 50% HP.",3,4),
                    new Set("Martial Artist","Normal and Charged Attack DMG +15%", "After using Elemental Skill, increases Normal Attack and Charged Attack DMG by 25% for 8s.", 3,4),
                    new Set("Gambler", "Increases Elemental Skill DMG by 20%", "Defeating an opponent has 100% chance to remove Elemental Skill CD. Can only occur once every 15s.", 3,4),
                    new Set("Scholar", "Energy Recharge +20%", "Gaining Elemental Particles or Orbs gives 3 Energy to all party members who have a bow or a catalyst equipped. Can only occur once every 3s.", 3,4),
                    new Set("Prayers for Wisdom", "Affected by Electro for 40% less time.", "No four piece set bonus.",3,4),
                    new Set("Prayers for Destiny", "Affected by Hydro for 40% less time.", "No four piece set bonus.",3,4),
                    new Set("Prayers for Illumination", "Affected by Pyro for 40% less time.", "No four piece set bonus.",3,4),
                    new Set("Prayers to Springtime", "Affected by Cryo for 40% less time.", "No four piece set bonus.",3,4),
                    new Set("Gladiator's Finale", "ATK +18%", "If the wielder of this artifact set uses a Sword, Claymore or Polearm, increases their Normal Attack DMG by 35%.",4,5),
                    new Set("Wanderer's Troupe", "Increases Elemental Mastery by 80", "Increases Charged Attack DMG by 35% if the character uses a Catalyst or Bow.",4,5),
                    new Set("Noblesse Oblige", "Elemental Burst DMG +20%", "Using an Elemental Burst increases all party members' ATK by 20% for 12s. This effect cannot stack.",4,5),
                    new Set("Bloodstained Chivalry","Physical DMG Bonus +25%", "After defeating an opponent, increases Charged Attack DMG by 50%, and reduces its Stamina cost to 0 for 10s. Also triggers with wild animals such as boars, squirrels and frogs.",4,5),
                    new Set("Maiden Beloved", "Character Healing Effectiveness +15%", "Using an Elemental Skill or Burst increases healing received by all party members by 20% for 10s.", 4,5),
                    new Set("Viridescent Venerer", "Anemo DMG Bonus +15%", "Increases Swirl DMG by 60%. Decreases opponent's Elemental RES to the element infused in the Swirl by 40% for 10s.",4,5),
                    new Set("Archaic Petra", "Geo DMG Bonus +15%","Upon obtaining an Elemental Shard created through a Crystallize Reaction, all party members gain 35% DMG Bonus for that particular element for 10s. Only one form of Elemental DMG Bonus can be gained in this manner at any one time.",4,5),
                    new Set("Retracing Bolide", "Increases Shield Strength by 35%", "While protected by a shield, gain an additional 40% Normal and Charged Attack DMG.",4,5),
                    new Set("Thundersoother", "Electro RES increased by 40%", "Increases DMG against opponents affected by Electro by 35%.",4,5),
                    new Set("Thundering Fury", "Electro DMG Bonus +15%", "Increases DMG caused by Overloaded, Electro-Charged, Superconduct, and Hyperbloom by 40%, and the DMG Bonus conferred by Aggravate is increased by 20%. When Quicken or the aforementioned Elemental Reactions are triggered, Elemental Skill CD is decreased by 1s. Can only occur once every 0.8s.",4,5),
                    new Set("Lavawalker", "Pyro RES increased by 40%", "Increases DMG against opponents affected by Pyro by 35%.",4,5),
                    new Set("Crimson Witch of Flames", "Pyro DMG Bonus +15%", "Increases Overloaded and Burning, and Burgeon DMG by 40%. Increases Vaporize and Melt DMG by 15%. Using Elemental Skill increases the 2-Piece Set Bonus by 50% of its starting value for 10s. Max 3 stacks.",4,5),
                    new Set("Blizzard Strayer", "Cryo DMG Bonus +15%", "When a character attacks an opponent affected by Cryo, their CRIT Rate is increased by 20%. If the opponent is Frozen, CRIT Rate is increased by an additional 20%.", 4,5),
                    new Set("Heart of Depth", "Hydro DMG Bonus +15%", "After using an Elemental Skill, increases Normal Attack and Charged Attack DMG by 30% for 15s.",4,5),
                    new Set("Tenacity of the Millelith", "HP +20%", "When an Elemental Skill hits an opponent, the ATK of all nearby party members is increased by 20% and their Shield Strength is increased by 30% for 3s. This effect can be triggered once every 0.5s. This effect can still be triggered even when the character who is using this artifact set is not on the field.",4,5),
                    new Set("Pale Flame", "Physical DMG Bonus +25%", "When an Elemental Skill hits an opponent, ATK is increased by 9% for 7s. This effect stacks up to 2 times and can be triggered once every 0.3s. Once 2 stacks are reached, the 2-set effect is increased by 100%.",4,5),
                    new Set("Shimenawa's Resistance", "ATK +18%", "When casting an Elemental Skill, if the character has 15 or more Energy, they lose 15 Energy and Normal/Charged/Plunging Attack DMG is increased by 50% for 10s. This effect will not trigger again during that duration.",4,5),
                    new Set("Emblem of Severed Fate", "Energy Recharge +20%", "Increases Elemental Burst DMG by 25% of Energy Recharge. A maximum of 75% bonus DMG can be obtained in this way.",4,5),
                    new Set("Husk of Opulent Dreams", "DEF +30%", "A character equipped with this Artifact set will obtain the Curiosity effect in the following conditions:\n" +
                            "When on the field, the character gains 1 stack after hitting an opponent with a Geo attack, triggering a maximum of once every 0.3s.\n" +
                            "When off the field, the character gains 1 stack every 3s.\n" +
                            "Curiosity can stack up to 4 times, each providing 6% DEF and a 6% Geo DMG Bonus.\n" +
                            "When 6 seconds pass without gaining a Curiosity stack, 1 stack is lost.",4,5),
                    new Set("Ocean-Hued Clam", "Healing Bonus +15%", "When the character equipping this artifact set heals a character in the party, a Sea-Dyed Foam will appear for 3 seconds, accumulating the amount of HP recovered from healing (including overflow healing).\n" +
                            "At the end of the duration, the Sea-Dyed Foam will explode, dealing DMG to nearby opponents based on 90% of the accumulated healing.\n" +
                            "(This DMG is calculated similarly to Reactions such as Electro-Charged, and Superconduct, but it is not affected by Elemental Mastery, Character Levels, or Reaction DMG Bonuses).\n" +
                            "Only one Sea-Dyed Foam can be produced every 3.5 seconds.\n" +
                            "Each Sea-Dyed Foam can accumulate up to 30,000 HP (including overflow healing).\n" +
                            "There can be no more than one Sea-Dyed Foam active at any given time.\n" +
                            "This effect can still be triggered even when the character who is using this artifact set is not on the field.",4,5),
                    new Set("Vermillion Hereafter", "ATK +18%", "After using an Elemental Burst. this character will gain the Nascent Light effect, increasing their ATK by 8% for 16s. When the character's HP decreases, their ATK will further increase by 10%. This increase can occur this way maximum of 4 times. This effect can be triggered once every 0.8s. Nascent Light will be dispelled when the character leaves the field. If an Elemental Burst is used again during the duration of Nascent Light, the original Nascent Light will be dispelled.",4,5),
                    new Set("Echoes of an Offering", "ATK +18%", "When Normal Attacks hit opponents, there is a 36% chance that it will trigger Valley Rite, which will increase Normal Attack DMG by 70% of ATK.\n" +
                            "This effect will be dispelled 0.05s after a Normal Attack deals DMG.\n" +
                            "If a Normal Attack fails to trigger Valley Rite, the odds of it triggering the next time will increase by 20%.\n" +
                            "This trigger can occur once every 0.2s.",4,5),
                    new Set("Deepwood Memories", "Dendro DMG Bonus +15%", "After Elemental Skills or Bursts hit opponents, the targets' Dendro RES will be decreased by 30% for 8s. This effect can be triggered even if the equipping character is not on the field.",4,5),
                    new Set("Gilded Dreams", "Increases Elemental Mastery by 80", "Within 8s of triggering an Elemental Reaction, the character equipping this will obtain buffs based on the Elemental Type of the other party members. ATK is increased by 14% for each party member whose Elemental Type is the same as the equipping character, and Elemental Mastery is increased by 50 for every party member with a different Elemental Type. Each of the aforementioned buffs will count up to 3 characters. This effect can be triggered once every 8s. The character who equips this can still trigger its effects when not on the field.",4,5),
                    new Set("Desert Pavillion Chronicle", "Anemo DMG Bonus +15%", "When Charged Attacks hit opponents, the equipping character's Normal Attack SPD will increase by 10% while Normal, Charged, and Plunging Attack DMG will increase by 40% for 15s.",4,5),
                    new Set("Flower of Paradise Lost", "Increases Elemental Mastery by 80", "The equipping character's Bloom, Hyperbloom, and Burgeon reaction DMG are increased by 40%. Additionally, after the equipping character triggers Bloom, Hyperbloom, or Burgeon, they will gain another 25% bonus to the effect mentioned prior. Each stack of this lasts 10s. Max 4 stacks simultaneously. This effect can only be triggered once per second. The character who equips this can still trigger its effects when not on the field.",4,5),
                    new Set("Nymph's Dream", "Hydro DMG Bonus +15%", "After Normal, Charged, and Plunging Attacks, Elemental Skills, and Elemental Bursts hit opponents. 1 stack of Mirrored Nymph will triggered, lasting 8s. When under the effect of 1, 2, or 3 or more Mirrored Nymph stacks, ATK will be increased by 7%/16%/25%, and Hydro DMG will be increased by 4%/9%/15%. Mirrored Nymph created by Normal, Charged, and Plunging Attacks, Elemental Skills, and Elemental Bursts exist independently.",4,5),
                    new Set("Vorukasha's Glow", "HP +20%", "Elemental Skill and Elemental Burst DMG will be increased by 10%. After the equipping character takes DMG, the aforementioned DMG Bonus is increased by 80% for 5s. This effect increase can have 5 stacks. The duration of each stack is counted independently. These effects can be triggered even when the equipping character is not on the field.",4,5),
                    new Set("Marechaussee Hunter", "Normal and Charged Attack DMG +15%", "When current HP increases or decreases, CRIT Rate will be increased by 12% for 5s. Max 3 stacks.",4,5),
                    new Set("Golden Troupe", "Increases Elemental Skill DMG by 20%", "Increases Elemental Skill DMG by 25%. Additionally, when not on the field, Elemental Skill DMG will be further increased by 25%. This effect will be cleared 2s after taking the field.",4,5)
            );
            Toast.makeText(getApplicationContext(), "Populating Artifact Sets", Toast.LENGTH_LONG).show();
        }
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}