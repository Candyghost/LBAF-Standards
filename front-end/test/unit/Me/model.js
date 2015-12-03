describe('UNIT: Me Model', function() {

    var Me;

    beforeEach(module('app'));
    beforeEach(inject(function(_$httpBackend_, _Me_){
        Me = _Me_;
    }));


    it('# : has rights', function () {
        var me = Me.get();
        me.rights = [];
        expect(me.hasRight(Me.const.RIGHTS.GET_ALL_BOOK)).toBe(false);
        me.rights = [Me.const.RIGHTS.GET_ALL_BOOK];
        expect(me.hasRight(Me.const.RIGHTS.GET_ALL_BOOK)).toBe(true);
    });

});