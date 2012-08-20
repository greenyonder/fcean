package falcofinder.android.fce;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionAdapter extends ArrayAdapter<Question> {
	
    private ArrayList<Question> questions;
    private final Context mContext;
    
    public QuestionAdapter(Context context, int textViewResourceId, ArrayList<Question> questions) {
        super(context, textViewResourceId, questions);
        mContext = context;
        this.questions = questions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
  if (v == null) {
            LayoutInflater vi = (LayoutInflater)  getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.listitem, null);
  }
     
  Question q = questions.get(position);
  	if (questions != null) {
            TextView question = (TextView) v.findViewById(R.id.txtquestion);
            TextView youranswer = (TextView) v.findViewById(R.id.txtyouranswer);
            TextView rightanswer = (TextView) v.findViewById(R.id.rightanswer);
            
      if (q != null) {
    	
			 
			 question.setText(new SpannableString("Question: " + Util.replace(Util.replace(q.question,"."," .") + " "," " + q.rightanswer + " ","________")));
			 youranswer.setText(new SpannableString("Your answer: " + q.youranswer));
			 rightanswer.setText(new SpannableString("Right answer: " + q.rightanswer));
			 
      }

     
   // System.out.println("--->d.image"+d.image.substring(0, d.image.indexOf(".")).trim() + "-");
      
 		
    	  
      
  }
  return v;
    }
}

