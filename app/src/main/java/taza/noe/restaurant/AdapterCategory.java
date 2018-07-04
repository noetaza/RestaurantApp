package taza.noe.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategory extends ArrayAdapter implements View.OnClickListener{

    protected ArrayList<Category> items;
    private Context context;

    private LayoutInflater inflater;

    public AdapterCategory (ArrayList<Category> items, Context context) {
        super(context, R.layout.item_category, items);
        this.items = items;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_category, parent, false);
        }

        Category dir = items.get(position);

        TextView name = convertView.findViewById(R.id.textName);
        name.setText(dir.getName());

        //TextView description = convertView.findViewById(R.id.textDescription);
        //description.setText(dir.getDescription());

        TextView price = convertView.findViewById(R.id.textPrice);
        price.setText("S/ "+String.valueOf(dir.getPrice()));

        ImageView image = convertView.findViewById(R.id.imageDish);

        Picasso
                .with(context)
                .load(dir.getImage())
                .placeholder(R.drawable.ic_autorenew_black)
                .error(R.drawable.ic_error_outline_black)
                .resize(100,100)
                .centerCrop()
                .into(image);

        //CheckBox checkBox = convertView.findViewById(R.id.checkBox);


        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
