import{a as B,c as w,d as G,e as H,g as _,h as J,i as Q,j as q,l as W,m as Z,n as $,o as K}from"./chunk-FBDWB4UQ.js";import{a as P,b as L,c as z}from"./chunk-ZCGLQAZY.js";import{a as F}from"./chunk-QGG2LZ3E.js";import{C as u,D as S,La as N,N as R,Na as A,Pa as k,S as c,T as d,X as x,Z as f,ba as C,d as j,ea as r,eb as T,fa as s,ga as l,gb as V,la as D,ma as h,pa as m,ra as b,sa as U,w as O,x as y,z as M}from"./chunk-JPZ7LBG3.js";function se(t,e){if(t&1&&m(0),t&2){let a=h();b(" ",a.user.company_name," ")}}function me(t,e){if(t&1&&m(0),t&2){let a=h();U(" ",a.user.first_name," ",a.user.last_name," ")}}function ae(t,e){if(t&1&&m(0),t&2){let a=h();b(" ",a.user.description," ")}}function le(t,e){if(t&1&&m(0),t&2){let a=h();b(" ",a.user.title," ")}}function pe(t,e){if(t&1&&m(0),t&2){let a=h();b(" ",a.user.admin_title," ")}}var X=(()=>{let e=class e{constructor(n){this.users=n,this.PAR=localStorage.getItem("PAR")}};e.\u0275fac=function(i){return new(i||e)(d(F))},e.\u0275cmp=u({type:e,selectors:[["app-profile-overview"]],inputs:{user:"user"},decls:9,vars:3,consts:[[1,"bg-white","p-4","rounded-lg","shadow","text-center"],["alt","Profile","height","80","priority","","width","80",1,"rounded-full","mx-auto","mb-4",3,"ngSrc"],[1,"text-xl","font-semibold"],[1,"text-sm","text-gray-500"]],template:function(i,o){i&1&&(r(0,"div",0),l(1,"img",1),r(2,"h3",2),x(3,se,1,1)(4,me,1,2),s(),r(5,"p",3),x(6,ae,1,1)(7,le,1,1)(8,pe,1,1),s()()),i&2&&(c(),f("ngSrc",o.PAR+o.user.image_name),c(2),C(3,o.users.isCompany(o.user)?3:o.users.isJobseeker(o.user)||o.users.isAdmin(o.user)?4:-1),c(3),C(6,o.users.isCompany(o.user)?6:o.users.isJobseeker(o.user)?7:o.users.isAdmin(o.user)?8:-1))},dependencies:[A]});let t=e;return t})();var I=(()=>{let e=class e{constructor(n){this.http=n,this.api=V.hostUrl+"/posts"}getLatestPosts(){return this.http.get(this.api+"/latest")}getPostById(n){return this.http.get(this.api+"/"+n)}createPost(n){return new Promise((i,o)=>{setTimeout(()=>{i(!0)},1e3)})}};e.\u0275fac=function(i){return new(i||e)(M(k))},e.\u0275prov=O({token:e,factory:e.\u0275fac});let t=e;return t})();function ue(t,e){if(t&1&&l(0,"img",11),t&2){let a=h();f("src",a.imagePreview,R)}}var Y=(()=>{let e=class e{constructor(n,i,o,p){this.utils=n,this.fb=i,this.forms=o,this.posts=p,this.content=new _("",[w.required,w.minLength(5),w.maxLength(500),w.pattern(/^[a-zA-Z0-9\s]+$/)]),this.image=new _(null),this.postForm=this.fb.group({content:this.content,image:this.image})}onImagePicked(n){let i=n.target,o=i.files[0];if(i&&o){let p=new Image;p.src=window.URL.createObjectURL(o),p.onload=()=>{try{let v=o.size/1024,oe=p.width>500||p.height>500||p.width<100||p.height<100,ne=v>3e3;if(oe)throw new Error("Les dimensions de l'image doivent \xEAtre comprises entre 100x100 et 500x500");if(ne)throw new Error("La taille de l'image ne doit pas d\xE9passer 3Mo");let E=new FileReader;E.onload=()=>this.imagePreview=E.result,E.readAsDataURL(o),this.postForm.patchValue({image:o})}catch(v){this.utils.alert(v.message,P.ERROR)}}}}submitPost(){return j(this,null,function*(){this.forms.checkFormValidity(this.postForm)&&(yield this.posts.createPost(this.postForm.value).then(()=>(this.utils.alert("Post cr\xE9\xE9 avec succ\xE8s",P.SUCCESS),this.utils.playSound(L.POST),!0)).catch(i=>(this.utils.alert(i.error.error,P.ERROR),!1)))&&this.postForm.reset()})}};e.\u0275fac=function(i){return new(i||e)(d(z),d(W),d(K),d(I))},e.\u0275cmp=u({type:e,selectors:[["app-post-form"]],decls:12,vars:3,consts:[[1,"bg-white","p-4","rounded-lg","shadow-md","w-full","mb-4",3,"formGroup","ngSubmit"],[1,"font-semibold","mb-2"],[1,"mb-4"],["name","content","placeholder","Quoi de neuf ?",1,"textarea","w-full","mb-3","focus:h-40",3,"formControl"],["class","w-52 h-52 rounded-lg mx-auto my-2","alt","Uploaded Image",3,"src"],[1,"flex","justify-end","space-x-2","text-gray-400"],["for","imageUpload",1,"flex","btn","btn-ghost","btn-circle","cursor-pointer"],[1,"bi","bi-camera","text-xl"],["type","file","name","image","id","imageUpload",1,"hidden",3,"change"],["type","submit",1,"btn","btn-primary","btn-circle"],[1,"bi","bi-send-fill","text-lg"],["alt","Uploaded Image",1,"w-52","h-52","rounded-lg","mx-auto","my-2",3,"src"]],template:function(i,o){i&1&&(r(0,"form",0),D("ngSubmit",function(){return o.submitPost()}),r(1,"h1",1),m(2,"Nouvelle publication"),s(),l(3,"hr",2)(4,"textarea",3),x(5,ue,1,1,"img",4),r(6,"div",5)(7,"label",6),l(8,"i",7),r(9,"input",8),D("change",function(v){return o.onImagePicked(v)}),s()(),r(10,"button",9),l(11,"i",10),s()()()),i&2&&(f("formGroup",o.postForm),c(4),f("formControl",o.content),c(),C(5,o.imagePreview?5:-1))},dependencies:[J,B,G,H,Q,q]});let t=e;return t})();var ee=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275cmp=u({type:e,selectors:[["app-posts-feed"]],decls:28,vars:0,consts:[[1,"bg-white","p-4","rounded-lg","shadow"],[1,"text-sm","text-gray-500","mb-2"],[1,"flex","items-start","mb-2"],["src","","alt","Profile",1,"w-12","h-12","rounded-full","mr-4"],[1,"text-lg","font-semibold"],[1,"text-sm","text-gray-500"],[1,"mt-2"],["href","#",1,"text-blue-500","mt-2","inline-block"],[1,"flex","justify-between","items-center","mt-4"],[1,"flex","items-center","space-x-4"],[1,"flex","items-center","space-x-2"],[1,"text-blue-500"],[1,"btn","btn-outline","btn-sm"]],template:function(i,o){i&1&&(r(0,"div",0)(1,"p",1),m(2,"Ted Bell, Annette Nguyen and Cody Hawkins liked this"),s(),r(3,"div",2),l(4,"img",3),r(5,"div")(6,"h3",4),m(7,"Theresa Steward"),s(),r(8,"p",5),m(9,"iOS developer"),s(),r(10,"p",6),m(11,"What did the Dursleys care if Harry lost his place on the House Quidditch team because he hadn\u2019t practiced all summer?"),s(),r(12,"a",7),m(13,"Read more"),s()()(),r(14,"div",8)(15,"div",9)(16,"button",10)(17,"span",11),m(18,"\u{1F44D}"),s(),r(19,"span"),m(20,"42"),s()(),r(21,"button",10)(22,"span",11),m(23,"\u{1F4AC}"),s(),r(24,"span"),m(25,"9"),s()()(),r(26,"button",12),m(27,"Share"),s()()())}});let t=e;return t})();var te=(()=>{let e=class e{constructor(n){this.users=n}ngOnInit(){this.users.currentUser.subscribe(n=>{n&&(this.user=n)})}};e.\u0275fac=function(i){return new(i||e)(d(F))},e.\u0275cmp=u({type:e,selectors:[["app-feed"]],decls:6,vars:1,consts:[[1,"mx-auto","p-4","flex","gap-6","w-3/4"],[1,"w-3/4"],[1,"w-1/4","space-y-4"],[3,"user"]],template:function(i,o){i&1&&(r(0,"div",0)(1,"div",1),l(2,"app-post-form")(3,"app-posts-feed"),s(),r(4,"div",2),l(5,"app-profile-overview",3),s()()),i&2&&(c(5),f("user",o.user))},dependencies:[X,Y,ee]});let t=e;return t})();var ge=[{path:"",children:[{path:"",redirectTo:"feed",pathMatch:"full"},{path:"feed",component:te,title:"Fil d'actualit\xE9 - LinkedOut"}]}],ie=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275mod=S({type:e}),e.\u0275inj=y({imports:[T.forChild(ge),T]});let t=e;return t})();var He=(()=>{let e=class e{};e.\u0275fac=function(i){return new(i||e)},e.\u0275mod=S({type:e}),e.\u0275inj=y({providers:[I],imports:[N,ie,Z,$]});let t=e;return t})();export{He as SocialModule};
