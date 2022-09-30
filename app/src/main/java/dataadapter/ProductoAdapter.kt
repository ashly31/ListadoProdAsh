package dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dataclass.Producto
import ni.edu.uca.listadoprod.databinding.ItemlistaBinding

class ProductoAdapter(
    val listProd:List<Producto>,
    private val onclickerView:(Producto)->Unit,
    private val onclickerDel: (Int) -> Unit,
    private val onclickerActu:(Int)->Unit
) :
    RecyclerView.Adapter<ProductoAdapter.ProductoHolder>(){
    inner class ProductoHolder(val binding: ItemlistaBinding):
        RecyclerView.ViewHolder(binding.root){

        fun cargar(
            producto: Producto, onClickListener:(Producto)->Unit,
            onclickerDel: (Int) -> Unit,
            onclickerActu: (Int) -> Unit
        ){
            with(binding){
                tvCodProd.text = producto.id.toString()
                tvNombreProd.text = producto.nombre
                tvPrecioProd.text = producto.precio.toString()
                itemView.setOnClickListener{onClickListener(producto)}
                binding.btnEliminar.setOnClickListener { onclickerDel(adapterPosition)}
                binding.btnEditar.setOnClickListener { onclickerActu(adapterPosition) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoHolder {
        val binding = ItemlistaBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false

        )
        return ProductoHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoHolder, position: Int) {
        holder.cargar(listProd[position], onclickerView, onclickerDel, onclickerActu)
    }

    override fun getItemCount(): Int = listProd.size


}