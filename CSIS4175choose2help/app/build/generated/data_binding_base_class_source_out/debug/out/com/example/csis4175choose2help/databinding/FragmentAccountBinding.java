// Generated by view binder compiler. Do not edit!
package com.example.csis4175choose2help.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.csis4175choose2help.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAccountBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final LinearLayout LinearLayoutFragment;

  @NonNull
  public final ImageView imgViewAvatar;

  @NonNull
  public final TextView txtViewLogOut;

  @NonNull
  public final TextView txtViewNavDonationHistory;

  @NonNull
  public final TextView txtViewNavDonationLinks;

  @NonNull
  public final TextView txtViewNavFindCharities;

  @NonNull
  public final TextView txtViewNavPayItForward;

  @NonNull
  public final TextView txtViewUserEmail;

  @NonNull
  public final TextView txtViewUserName;

  private FragmentAccountBinding(@NonNull FrameLayout rootView,
      @NonNull LinearLayout LinearLayoutFragment, @NonNull ImageView imgViewAvatar,
      @NonNull TextView txtViewLogOut, @NonNull TextView txtViewNavDonationHistory,
      @NonNull TextView txtViewNavDonationLinks, @NonNull TextView txtViewNavFindCharities,
      @NonNull TextView txtViewNavPayItForward, @NonNull TextView txtViewUserEmail,
      @NonNull TextView txtViewUserName) {
    this.rootView = rootView;
    this.LinearLayoutFragment = LinearLayoutFragment;
    this.imgViewAvatar = imgViewAvatar;
    this.txtViewLogOut = txtViewLogOut;
    this.txtViewNavDonationHistory = txtViewNavDonationHistory;
    this.txtViewNavDonationLinks = txtViewNavDonationLinks;
    this.txtViewNavFindCharities = txtViewNavFindCharities;
    this.txtViewNavPayItForward = txtViewNavPayItForward;
    this.txtViewUserEmail = txtViewUserEmail;
    this.txtViewUserName = txtViewUserName;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LinearLayoutFragment;
      LinearLayout LinearLayoutFragment = ViewBindings.findChildViewById(rootView, id);
      if (LinearLayoutFragment == null) {
        break missingId;
      }

      id = R.id.imgViewAvatar;
      ImageView imgViewAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imgViewAvatar == null) {
        break missingId;
      }

      id = R.id.txtViewLogOut;
      TextView txtViewLogOut = ViewBindings.findChildViewById(rootView, id);
      if (txtViewLogOut == null) {
        break missingId;
      }

      id = R.id.txtViewNavDonationHistory;
      TextView txtViewNavDonationHistory = ViewBindings.findChildViewById(rootView, id);
      if (txtViewNavDonationHistory == null) {
        break missingId;
      }

      id = R.id.txtViewNavDonationLinks;
      TextView txtViewNavDonationLinks = ViewBindings.findChildViewById(rootView, id);
      if (txtViewNavDonationLinks == null) {
        break missingId;
      }

      id = R.id.txtViewNavFindCharities;
      TextView txtViewNavFindCharities = ViewBindings.findChildViewById(rootView, id);
      if (txtViewNavFindCharities == null) {
        break missingId;
      }

      id = R.id.txtViewNavPayItForward;
      TextView txtViewNavPayItForward = ViewBindings.findChildViewById(rootView, id);
      if (txtViewNavPayItForward == null) {
        break missingId;
      }

      id = R.id.txtViewUserEmail;
      TextView txtViewUserEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtViewUserEmail == null) {
        break missingId;
      }

      id = R.id.txtViewUserName;
      TextView txtViewUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtViewUserName == null) {
        break missingId;
      }

      return new FragmentAccountBinding((FrameLayout) rootView, LinearLayoutFragment, imgViewAvatar,
          txtViewLogOut, txtViewNavDonationHistory, txtViewNavDonationLinks,
          txtViewNavFindCharities, txtViewNavPayItForward, txtViewUserEmail, txtViewUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}