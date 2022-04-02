import { useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import NavBar from './NavBar';

const axios = require('axios')


export default function AddProduct() {

    const [dataProduct, setDataProduct] = useState([])
    const [infoProduct, setInfoProduct] = useState([])
    const [dataFactory, setDataFactory] = useState([])

    const location = useLocation().state

    useEffect(() => {

        let a = 10

        axios.get(`/products/${location}`)
            .then(res => {
                setInfoProduct(res.data[0])
                a = 100
                console.log(res.data)
            })
            .catch(err => console.log('123 :', err))

        console.log(a)

        axios.get(`/products`)
            .then(res => setDataProduct(res.data))
            .catch(err => console.log('456 :', err))


        axios.get(`/factory`)
            .then(res => {
                setDataFactory(res.data)
                console.log('dataFactory : ', dataFactory)
            })
            .catch(err => console.log('Đây là lỗi xyz :', err))

    }, [])

    console.log('re-render')

    function handleEditProduct(e) {
        let name = document.getElementById('nameProduct').value
        let bonus = document.getElementById('bonusProduct').value
        let type = document.getElementById('typeProduct').value
        let material = document.getElementById('material').value
        let price = document.getElementById('price').value
        let discount = document.getElementById('discount').value
        let manufactory = document.getElementById('manufactory').value
        let guaranteeTime = document.getElementById('guaranteeTime').value
        let debutTime = document.getElementById('debutTime').value

        e.preventDefault();
        // console.log({
        //     name: name,
        //     productBonus: bonus == 'Lựa chọn' ? null : {name : bonus},
        //     type: type,
        //     discount: (discount == 'Không' || discount == '') ? null : discount,
        //     factory: dataFactory.find(item => item.name == manufactory),
        //     price: price,
        //     guaranteeTime: guaranteeTime,
        //     material: material,
        //     debutTime: debutTime
        // })

        if (name == '' || type == '' || material == '' || price == ''  || guaranteeTime == '' || manufactory == 'Lựa chọn') {
            toast.error('Thông tin chưa được điền đầy đủ', {
                position: "top-right",
                autoClose: 3000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
            });
            return
        }

        axios.put(`/products/update/${location}`, {
                    name: name,
                    productBonus: bonus == 'Lựa chọn' ? null : {name : bonus},
                    type: type,
                    discount: (discount == 'Không' || discount == '') ? null : discount,
                    factory: dataFactory.find(item => item.name == manufactory),
                    price: price,
                    guaranteeTime: guaranteeTime,
                    material: material,
                    debutTime: debutTime
                })
            .then(res => window.location.href = '/')

    }

    return (
        <div >
            <div class='mb-5'>
                <NavBar pathName='' />
            </div>
            <div class='container my-5' >

                <h2 class='d-flex justify-content-center text-primary my-5'>Chỉnh sửa sản phẩm</h2>

                <form class="row g-3 d-flex justify-content-center " method='PUT'>
                    <div class="col-md-5">
                        <label for="nameProduct" class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="nameProduct" defaultValue={infoProduct.name} change />
                    </div>
                    <div class="col-md-5">
                        <label for="bonusProduct" class="form-label">Sản phẩm tặng kèm</label>
                        <select id="bonusProduct" class="form-select">
                            {/*{infoProduct.productBonus ? <option selected>{infoProduct.productBonus.name}</option>*/}
                            {/*    : <option selected>Không</option>*/}
                            {/*}*/}
                            {/*{dataProduct.filter(item => item.name != infoProduct.productBonus.name).map(item => (*/}
                            {/*    <option>{item.name}</option>*/}
                            {/*))}*/}
                            <option>Lựa chọn</option>
                            {dataProduct.map(item => (
                                <option>{item.name}</option>
                            ))}
                        </select>
                    </div>
                    <div class="col-10">
                        <label for="typeProduct" class="form-label">Loại sản phẩm</label>
                        <input type="text" class="form-control" id="typeProduct" defaultValue={infoProduct.type} />
                    </div>
                    <div class="col-10">
                        <label for="material" class="form-label">Chất liệu</label>
                        <input type="text" class="form-control" id="material" defaultValue={infoProduct.material} />
                    </div>
                    <div class="col-md-10">
                        <label for="price" class="form-label">Giá bán</label>
                        <input type="text" class="form-control" id="price" defaultValue={infoProduct.price} />
                    </div>
                    <div class="col-md-10">
                        <label for="discount" class="form-label">Khuyến mãi </label>
                        <input type="text" class="form-control" id="discount"
                            defaultValue={infoProduct.discount} />
                    </div>
                    <div class="col-md-10">
                        <label for="manufactory" class="form-label">Hãng sản xuất</label>
                        <select id="manufactory" class="form-select">
                            <option>Lựa chọn</option>
                            {dataFactory.map(item =>
                                <option >{item.name}</option>)}
                            {/*{dataFactory.filter(item => item.name != infoProduct.factory.name).map(item => (*/}
                            {/*    // <option>{item.factory.name}</option>*/}
                            {/*    console.log(dataFactory)*/}
                            {/*))}*/}
                        </select>
                    </div>
                    <div class="col-md-10">
                        <label for="guaranteeTime" class="form-label">Thời gian bảo hành </label>
                        <input type="text" class="form-control" id="guaranteeTime" defaultValue={infoProduct.guaranteeTime} />
                    </div>
                    <div class="col-md-10">
                        <label for="debutTime" class="form-label">Thời gian ra mắt </label>
                        <input type="text" class="form-control" id="debutTime" defaultValue={(infoProduct.debutTime)} />
                    </div>
                    <div class="col-10 d-flex justify-content-end">
                        <button type="submit" class="btn btn-success w-25" onClick={(e) => handleEditProduct(e)}>Chỉnh sửa sản phẩm</button>
                        <ToastContainer />
                    </div>

                </form>
            </div>
        </div>
    )
}