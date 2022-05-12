package com.mis.acmebe;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.mis.acmebe.adapter.VueloAdapter;
import com.mis.acmebe.entity.Vuelo;

public class FirestoreService {
    private static String userId;
    private static FirebaseFirestore mDatabase;
    private static FirestoreService service;

    public static FirestoreService getServiceInstance(){
        if (service ==null || mDatabase == null){
            mDatabase = FirebaseFirestore.getInstance();
        }
        if (userId==null || userId.isEmpty()){
            userId= FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "";
        }
        service = new FirestoreService();
        return service;
    }
    public void saveTravel(Vuelo vuelo, OnCompleteListener<DocumentReference> listener){
        mDatabase.collection("travels").document(userId).collection("travel").add(vuelo).addOnCompleteListener(listener);
    }
    public void getTravelsFiltered(OnCompleteListener<QuerySnapshot> querySnapshotOnCompleteListener){
        mDatabase.collection("travels").document("travel").collection("1").whereGreaterThanOrEqualTo("favorito",true).get().addOnCompleteListener(querySnapshotOnCompleteListener);
    }
    public void getTravels(OnCompleteListener<QuerySnapshot> querySnapshotOnCompleteListener){
        mDatabase.collection("travels").document("travel").collection("1").get().addOnCompleteListener(querySnapshotOnCompleteListener);
    }
    public void getOpinions(OnCompleteListener<QuerySnapshot> querySnapshotOnCompleteListener){
        mDatabase.collection("travels").document("travel").collection("1").document("1").collection("opiniones").get().addOnCompleteListener(querySnapshotOnCompleteListener);
    }
    public ListenerRegistration getTravels(EventListener<QuerySnapshot> querySnapshotOnCompleteListener){
        return mDatabase.collection("travels").document("travel").collection("1").addSnapshotListener(querySnapshotOnCompleteListener);
    }
    public ListenerRegistration getTravelsFiltered(EventListener<QuerySnapshot> querySnapshotOnCompleteListener){
        return mDatabase.collection("travels").document("travel").collection("1").whereGreaterThanOrEqualTo("favorito",true).addSnapshotListener(querySnapshotOnCompleteListener);
    }
    public ListenerRegistration getOpinions(EventListener<QuerySnapshot> querySnapshotOnCompleteListener){
        return mDatabase.collection("travels").document("travel").collection("1").document("1").collection("opiniones").addSnapshotListener(querySnapshotOnCompleteListener);
    }

}
