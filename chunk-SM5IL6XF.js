import{a as S}from"./chunk-3HWXO5HQ.js";import"./chunk-U3N5WN3R.js";import{D as d,E as g,Ma as O,O as j,Oa as T,T as a,U as h,Y as C,_ as u,ca as b,fb as E,ga as i,ha as r,ia as p,oa as c,qa as M,sa as s,ta as f,ua as v,va as _,xa as y,y as x}from"./chunk-AB6ZHOOE.js";var F=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275cmp=d({type:e,selectors:[["app-about-profile"]],inputs:{user:"user"},standalone:!0,features:[y],decls:2,vars:0,template:function(o,l){o&1&&(i(0,"p"),s(1,"about works!"),r())}});let t=e;return t})();function A(t,e){if(t&1&&(i(0,"div",6)(1,"h2",7),s(2),i(3,"span",8),s(4,"out"),r()(),i(5,"p",9),s(6),r(),i(7,"div",10)(8,"div",11),p(9,"i",12),s(10),r()()()),t&2){let n=c();a(2),_("",n.user.first_name," ",n.user.last_name," "),a(4),f(n.user.title),a(4),v(" ",n.user.address," ")}}function q(t,e){if(t&1&&(i(0,"div",6)(1,"h2",7),s(2),i(3,"span",13),s(4," comp "),r()(),i(5,"p",9),s(6),r(),i(7,"div",14),p(8,"i",12),s(9),r(),i(10,"div",11),p(11,"i",15),i(12,"a",16),s(13),r()()()),t&2){let n=c();a(2),v("",n.user.company_name," "),a(4),f(n.user.sector),a(3),v(" ",n.user.headquarters," "),a(3),M("href","https://",n.user.website,"",j),a(),f(n.user.website)}}function B(t,e){if(t&1&&(i(0,"div",6)(1,"h2",7),s(2),i(3,"span",8),s(4,"admin"),r()(),i(5,"p",9),s(6),r(),i(7,"div",10)(8,"div",11),p(9,"i",12),s(10),r()()()),t&2){let n=c();a(2),_("",n.user.first_name," ",n.user.last_name," "),a(4),f(n.user.admin_title),a(4),v(" ",n.user.email," ")}}function J(t,e){t&1&&(i(0,"h2"),s(1," User type not recognized "),r())}var k=(()=>{let e=class e{constructor(m){this.users=m}};e.\u0275fac=function(o){return new(o||e)(h(S))},e.\u0275cmp=d({type:e,selectors:[["app-overview-profile"]],inputs:{user:"user"},standalone:!0,features:[y],decls:9,vars:2,consts:[[1,"bg-white","shadow","rounded","overflow-hidden"],[1,"relative"],["ngSrc","../../../../../assets/images/profile_banner.png","width","1584","height","396","priority","","alt","Background Image",1,"w-full","h-32","object-cover"],[1,"rounded-full","border-4","border-white"],["width","96","height","96","priority","","alt","Profile Image",1,"w-24","h-24","rounded-full","object-cover",3,"ngSrc"],["class","p-4"],[1,"p-4"],[1,"text-xl","font-bold"],[1,"badge","badge-info"],[1,"text-gray-600"],[1,"flex","items-center","justify-between","mt-4"],[1,"text-sm","text-gray-600","flex","items-center"],[1,"bi","bi-geo-alt-fill","text-lg","me-2"],[1,"badge","badge-accent"],[1,"text-sm","text-gray-600","flex","items-center","mt-2"],[1,"bi","bi-box-arrow-up-right","text-lg","me-2"],["target","_blank",1,"link","link-primary",3,"href"]],template:function(o,l){if(o&1&&(i(0,"div",0)(1,"div",1),p(2,"img",2),i(3,"div",3),p(4,"img",4),r()(),C(5,A,11,4,"div",5)(6,q,14,5)(7,B,11,4)(8,J,2,0),r()),o&2){let I;a(4),u("ngSrc",(I=l.user.image_url)!==null&&I!==void 0?I:"assets/images/user.png"),a(),b(5,l.users.isJobseeker(l.user)?5:l.users.isCompany(l.user)?6:l.users.isAdmin(l.user)?7:8)}},dependencies:[T]});let t=e;return t})();function G(t,e){if(t&1&&p(0,"app-overview-profile",2)(1,"app-about-profile",2),t&2){let n=c();u("user",n.user),a(),u("user",n.user)}}var D=(()=>{let e=class e{constructor(m){this.userServ=m}ngOnInit(){this.userServ.currentUser.subscribe(m=>{m&&(this.user=m)})}};e.\u0275fac=function(o){return new(o||e)(h(S))},e.\u0275cmp=d({type:e,selectors:[["app-profile"]],decls:3,vars:1,consts:[[1,"container","m-8"],[1,"gap-4","columns-2","mx-12"],[1,"my-1","w-full",3,"user"]],template:function(o,l){o&1&&(i(0,"div",0)(1,"div",1),C(2,G,2,2),r()()),o&2&&(a(2),b(2,l.user?2:-1))},dependencies:[F,k]});let t=e;return t})();var H=[{path:"",component:D}],N=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=g({type:e}),e.\u0275inj=x({imports:[E.forChild(H),E]});let t=e;return t})();var re=(()=>{let e=class e{};e.\u0275fac=function(o){return new(o||e)},e.\u0275mod=g({type:e}),e.\u0275inj=x({imports:[O,N]});let t=e;return t})();export{re as ProfileModule};
